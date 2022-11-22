import {
  Flex,
  Text,
  Button,
  Heading,
  // Stack,
  // Textarea,
  // Divider,
  // Center,
  // Image,
  Accordion,
  AccordionPanel,
  AccordionIcon,
  AccordionItem,
  AccordionButton,
  Box,
  Input,
} from "@chakra-ui/react";
import dynamic from "next/dynamic";
import { React, useState, useRef } from "react";
// import { TypeAnimation } from "react-type-animation";
// import HCaptcha from "@hcaptcha/react-hcaptcha";
// import { FaLinkedin } from "react-icons/fa";
// import { HiMapPin } from "react-icons/hi2";
// import { IoMail } from "react-icons/io5";
// import { BrowserRouter } from "react-router-dom";
// import { HashLink } from "react-router-hash-link";
//Public Imports

//Component imports
import { HeroVideo } from "./indexStyles";
import { Navigate, useNavigate } from "react-router-dom";
const NavbarPeek = dynamic(() => import("../components/client/NavbarPeek"));

const Top5Trends = dynamic(() => import("../components/client/Top5Trends"));
const DailySearches = dynamic(() =>
  import("../components/client/DailySearches")
);
// const DateSelection = dynamic(() => import("../components/client/DateSelection"));
const MonthlySearches = dynamic(() =>
  import("../components/client/MonthlySearches")
);

const AccordionPeek = () => {
  return (
    <Flex direction="column" padding="40px">
      <Heading color="#fff" paddingLeft="50px">
        FAQs
      </Heading>
      <Flex padding="50px" justifyContent="center">
        <Accordion allowToggle color="#fff" w="100%">
          <AccordionItem>
            <h2>
              <AccordionButton>
                <Box flex="1" textAlign="left">
                  What can't I search?
                </Box>
                <AccordionIcon />
              </AccordionButton>
            </h2>
            <AccordionPanel pb={4}>
              Our search function is only accessible to premium subscribers.
            </AccordionPanel>
          </AccordionItem>

          <AccordionItem>
            <h2>
              <AccordionButton>
                <Box flex="1" textAlign="left">
                  What the difference between free and premium?
                </Box>
                <AccordionIcon />
              </AccordionButton>
            </h2>
            <AccordionPanel pb={4}>
              Premium features will provide you with more data, including
              sentiments analysis, retweets, and ability to search.
            </AccordionPanel>
          </AccordionItem>

          <AccordionItem>
            <h2>
              <AccordionButton>
                <Box flex="1" textAlign="left">
                  Am I able to export data out?
                </Box>
                <AccordionIcon />
              </AccordionButton>
            </h2>
            <AccordionPanel pb={4}>
              No, but do expect updates in the future for this function
            </AccordionPanel>
          </AccordionItem>
          <AccordionItem>
            <h2>
              <AccordionButton>
                <Box flex="1" textAlign="left">
                  How much is the premium plan?
                </Box>
                <AccordionIcon />
              </AccordionButton>
            </h2>
            <AccordionPanel pb={4}>$14.99 per month</AccordionPanel>
          </AccordionItem>
        </Accordion>
      </Flex>
    </Flex>
  );
};
const HeroPeek = () => {
  const [showError, setShowError] = useState(false);
  return (
    <>
      {/* <BrowserRouter> */}
      <Flex flexDir="column">
        <Flex className="HeroPage" id="hero" flexDir="row">
          <Flex px="lg" flexBasis="25%" className="left" bgColor="gray.400">
            <Flex padding="30px" flexDir="column" className="search">
              <Heading fontSize="2rem">Search for trends!</Heading>
              <Text color="gray.700">i.e. #forsale #clothes #phonecase</Text>
              <Flex direction="column" py="20px">
                <Input placeholder="Search!" bgColor="#fff" w="250px" />
                <Flex py="10px" w="150px">
                  <Button
                    colorScheme="facebook"
                    onClick={() => {
                      setShowError(true);
                    }}>
                    Search
                  </Button>
                </Flex>
              </Flex>
              <Flex className="Error">
                {showError ? (
                  <Text color="red">
                    Search only available for premium accounts. Upgrade to
                    Premium!
                  </Text>
                ) : (
                  ""
                )}
              </Flex>
            </Flex>
          </Flex>
          <Top5Trends />
        </Flex>
        <AccordionPeek />
      </Flex>
      {/* </BrowserRouter> */}
    </>
  );
};
const Peek = () => {
  return (
    <Flex className="Layout Parent" id="top" minWidth="100%" direction="column">
      <Flex
        width="100%"
        flexDir="column"
        className="Content Parent"
        bgColor="#0A1639">
        <NavbarPeek />
        <HeroPeek />
      </Flex>
    </Flex>
  );
};

export default Peek;
