import { Flex, Text, Button, Heading, Image } from "@chakra-ui/react";
import dynamic from "next/dynamic";
import { React, useState, useRef } from "react";
import { TypeAnimation } from "react-type-animation";
import { HashLink } from "react-router-hash-link";
//Component imports
import { HeroVideo } from "./indexStyles";
import { BrowserRouter, Navigate, useNavigate } from "react-router-dom";
const Navbar = dynamic(() => import("../components/client/Navbar"));
const AboutUs = dynamic(() => import("../components/client/AboutUs"));
const SignUp = dynamic(() => import("../components/client/Signup"));
const ContactUs = dynamic(() => import("../components/client/ContactUs"));
const Hero = () => {
  return (
    <>
      <Flex
        className="HeroPage"
        id="hero"
        flexDir="column"
        height="89vh"
        justifyContent="center"
        alignItems="center">
        <Flex px="lg" height="20vh">
          <TypeAnimation
            sequence={[
              "Don't know what to do?",
              3000,
              "We produce ideas for you!",
              3000,
              "Get going now :)",
              3000,
            ]}
            speed={40}
            wrapper="span"
            cursor={true}
            repeat={Infinity}
            style={{ fontSize: "5em", color: "#fff", padding: "lg" }}
          />
        </Flex>
        <Flex>
          {/* <HashLink to="#signup" smooth> */}
          <Button size="lg" colorScheme="facebook">
            Get started
          </Button>
          {/* </HashLink> */}
        </Flex>
      </Flex>
    </>
  );
};

const Home = () => {
  return (
    <BrowserRouter>
      <Flex
        className="Layout Parent"
        id="top"
        minWidth="100%"
        direction="column">
        <Flex width="100%" flexDir="column" className="Content Parent">
          <HeroVideo src="./hero.mp4" autoPlay loop muted />
          <Navbar />
          <Hero />
          <AboutUs />
          <SignUp />
          <ContactUs />
        </Flex>
      </Flex>
    </BrowserRouter>
  );
};

export default Home;
