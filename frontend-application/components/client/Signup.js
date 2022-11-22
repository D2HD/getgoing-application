import {React, useState, useRef} from "react";
import { Flex, Heading, Stack, Input, Button } from "@chakra-ui/react";
import HCaptcha from "@hcaptcha/react-hcaptcha";

const SignUp = () => {
  const [token, setToken] = useState(null);
  const captchaRef = useRef(null);
  return (
    <Flex
      className="Signup"
      id="signup"
      height="100vh"
      justifyContent="center"
      align="center">
      <Flex
        borderRadius="25px"
        bgColor="#D84C61"
        boxShadow="5px 10px"
        flexDir="column"
        py="25px"
        px="40px">
        <Heading
          fontSize="2xl"
          textAlign="center"
          opacity="1.25">
          Create Account
        </Heading>
        <Stack
          spacing={3}
          py="20px">
          <Flex justify="space-between">
            <Input
              placeholder="First Name"
              size="md"
              backgroundColor="#fff"
              flexBasis="50%"
            />
            <Input
              placeholder="Last Name"
              size="md"
              backgroundColor="#fff"
              flexBasis="45%"
            />
          </Flex>

          <Input
            placeholder="Last Name"
            size="md"
            backgroundColor="#fff"
          />
          <Input
            placeholder="Email Address"
            size="md"
            backgroundColor="#fff"
          />
          <Input
            placeholder="Create Password"
            size="md"
            backgroundColor="#fff"
          />
          <Input
            placeholder="Confirm Password"
            size="md"
            backgroundColor="#fff"
          />
          <HCaptcha
            w="500px"
            sitekey={`${process.env.NEXT_PUBLIC_HCAPTCHA_SITEKEY}`}
            onVerify={setToken}
            onExpire={(e) => setToken("")}
            ref={captchaRef}
          />
          <Button
            colorScheme="blackAlpha"
            size="md">
            Register
          </Button>
        </Stack>
      </Flex>
    </Flex>
  );
};

export default SignUp;