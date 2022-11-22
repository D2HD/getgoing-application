import {React, useState, useRef} from "react";
import { Flex, Heading, Stack, Input, Textarea, Button, Center, Divider } from "@chakra-ui/react";
import HCaptcha from "@hcaptcha/react-hcaptcha";
import { FaLinkedin } from "react-icons/fa";
import { HiMapPin } from "react-icons/hi2";
import { IoMail } from "react-icons/io5";
const ContactUs = () => {
  const [token, setToken] = useState(null);
  const captchaRef = useRef(null);
  return (
    <Flex
      className="ContactUs"
      id="contactus"
      height="89vh"
      justifyContent="center"
      align="center">
      <Flex
        w="750px"
        borderRadius="25px"
        bgColor="#D84C61"
        boxShadow="5px 10px"
        flexDir="column"
        py="25px"
        px="40px">
        <Heading
          fontSize="2xl"
          textAlign="center">
          Any Questions? Get in touch!
        </Heading>
        <Flex>
          <Stack
            spacing={3}
            p="20px"
            flexBasis="60%">
            <Input
              placeholder="Full Name"
              size="md"
              backgroundColor="#fff"
            />
            <Input
              placeholder="Email"
              size="md"
              backgroundColor="#fff"
            />
            <Textarea
              placeholder="Message"
              size="sm"
              backgroundColor="#fff"></Textarea>
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
          <Center
            py="20px"
            height="fill">
            <Divider orientation="vertical" />
          </Center>

          <Flex
            flexDir="column"
            p="20px"
            flexBasis="40%">
            <Flex py="20px">
              <HiMapPin size="1.5rem" />
              <Heading
                px="5px"
                fontSize="lg">
                Singapore
              </Heading>
            </Flex>
            <Flex py="20px">
              <IoMail size="1.5rem" />
              <Heading
                px="5px"
                fontSize="lg">
                Email@hotmail.com
              </Heading>
            </Flex>
            <Flex py="20px">
              <FaLinkedin size="1.5rem" />
            </Flex>
          </Flex>
        </Flex>
      </Flex>
    </Flex>
  );
};

export default ContactUs;