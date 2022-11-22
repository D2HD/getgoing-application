import React from "react";
import { Flex, Text, Heading, Image, AspectRatio } from "@chakra-ui/react";

const AboutUs = () => {
  return (
    <Flex direction="column" p="75px">
      <Flex
        className="aboutUs"
        flexDir="row"
        padding="30px"
        borderRadius="25px"
        // background="rgba(94,23,235,0.8)"
        background="rgba(255,255,255, 0.9)">
        <Flex color="#fff" p="20px" flexDir="column">
          <Heading fontSize="3rem" color="rgba(94,23,235)">
            Providing you with latest ideas and trends.
          </Heading>
          <Flex
            flexDir="column"
            color="#fff"
            fontSize="1.1rem"
            fontWeight="300"
            paddingTop="1rem">
            <Text color="rgba(94,2,235)">
              Millions of Businesses use data to make informed decisions, we aim
              to provide the information to you as easy as possible. - use
              GetGoing's search engine to create data-driven models better
              strategizing your business and make e-commerce easier.
            </Text>
          </Flex>
        </Flex>
      </Flex>

      <Flex height="50px" w="100%"></Flex>
      <Flex
        className="free"
        flexDir="row"
        padding="30px"
        borderRadius="25px"
        background="rgba(255,255,255, 0.9)">
        <Flex maxW="700px" flexDir="column" alignItems="center">
          <video title="naturo" src="./FreeWalkthrough.mov" autoPlay loop />
        </Flex>
        <Flex color="#fff" w="50%" flexDir="column" p="20px">
          <Heading fontSize="3rem" color="rgba(94,23,235)">
            Free Account
          </Heading>
          <Flex
            flexDir="column"
            color="#fff"
            fontSize="1.1rem"
            fontWeight="300"
            paddingTop="1rem"
            w="80%">
            <Text color="rgba(94,23,235)">
              Sign up with us for free to see the top 5 most popular things sold
              at the moment as well as data analysis.
            </Text>
          </Flex>
        </Flex>
      </Flex>
      <Flex height="50px" w="100%"></Flex>
      <Flex
        className="premium"
        flexDir="row"
        padding="30px"
        borderRadius="25px"
        background="rgba(255,255,255, 0.9)">
        <Flex color="#fff" w="50%" flexDir="column" p="20px">
          <Heading fontSize="3rem" color="rgba(94,23,235)">
            Premium Accounts
          </Heading>
          <Flex
            flexDir="column"
            color="#fff"
            fontSize="1.1rem"
            fontWeight="300"
            paddingTop="1rem"
            w="80%">
            <Text color="rgba(94,23,235)">
              Premium features will provide you with more data, including
              sentiments analysis, retweets, and ability to search specific
              data.
            </Text>
          </Flex>
        </Flex>
        <Flex maxW="700px" flexDir="column" alignItems="center">
          <video title="naturo" src="./PremiumWalkthrough.mov" autoPlay loop />
        </Flex>
      </Flex>
    </Flex>
  );
};

export default AboutUs;
