import {
  Flex,
  Text,
  Button,
  Heading,
  Accordion,
  AccordionPanel,
  AccordionIcon,
  AccordionItem,
  AccordionButton,
  Box,
  Input,
} from "@chakra-ui/react";
import dynamic from "next/dynamic";
import { React, useState, useRef, useEffect } from "react";
// import { TypeAnimation } from "react-type-animation";
// import HCaptcha from "@hcaptcha/react-hcaptcha";
// import { FaLinkedin } from "react-icons/fa";
// import { HiMapPin } from "react-icons/hi2";
// import { IoMail } from "react-icons/io5";
// import { BrowserRouter } from "react-router-dom";
//Public Imports

//Component imports
import { Navigate, useNavigate } from "react-router-dom";
import UserService from "../freeServices/FreeUser";
const NavbarMember = dynamic(() => import("../components/client/NavbarMember"));
const PremiumTrends = dynamic(() =>
  import("../components/client/PremiumTrends")
);
const Member = () => {
  return (
    <Flex className="Layout Parent" id="top" minWidth="100%" direction="column">
      <Flex
        width="100%"
        flexDir="column"
        className="Content Parent"
        bgColor="#0A1639">
        <NavbarMember />
        <Main />
        <AccordionPeek />
      </Flex>
    </Flex>
  );
};

const Main = () => {
  const [inputList, setInputList] = useState("");
  const [stateObj, setStateObj] = useState("");
  const [search, setSearch] = useState("");
  const [print, setPrint] = useState(false);
  const handleChange = (event) => {
    setSearch(event.target.value);
  };
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
                <Input
                  placeholder="Search!"
                  bgColor="#fff"
                  w="250px"
                  onChange={handleChange}
                  value={search}
                />
                <Flex py="10px" w="150px">
                  <Button
                    colorScheme="facebook"
                    onClick={() => {
                      setPrint(true);
                      UserService.keywordSearchToTweetDtoList(search).then(
                        (Response) => {
                          setInputList({ users: Response.data });
                        }
                      );
                      UserService.keywordSearchToHashtagDto(search).then(
                        (Response) => {
                          setStateObj({ users: Response.data });
                        }
                      );
                      console.log(inputList);
                      console.log(stateObj);
                    }}>
                    Search
                  </Button>
                </Flex>
              </Flex>
            </Flex>
          </Flex>
          <Flex
            flexBasis="75%"
            className="right"
            flexDir="column"
            alignItems="center"
            bgColor="#fff">
            <Flex direction="column" padding="20px">
              <Flex>
                <PremiumTrends
                  searchTopic={inputList}
                  searchObj={stateObj}
                  printSearch={print}
                />
              </Flex>
            </Flex>
          </Flex>
        </Flex>
      </Flex>
      {/* </BrowserRouter> */}
    </>
  );
};

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
                  What is sentimental analysis?
                </Box>
                <AccordionIcon />
              </AccordionButton>
            </h2>
            <AccordionPanel pb={4}>
              Sentimental analysis shows a positive or negative sentiment in a
              tweets and determine the author's feelings in a sentence. The
              sentiment analysis score is returned as a real number between -1.0
              (negative) and 1.0 (positive).
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
                  Will there be any more features?
                </Box>
                <AccordionIcon />
              </AccordionButton>
            </h2>
            <AccordionPanel pb={4}>
              We would are continuing to develop more products to our system. We
              will keep you updated!
            </AccordionPanel>
          </AccordionItem>
        </Accordion>
      </Flex>
    </Flex>
  );
};

export default Member;
