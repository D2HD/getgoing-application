import React, { useState, Component, useRef } from "react";
//import dynamic from "next/dynamic";
import {
  Flex,
  Button,
  Text,
  Heading,
  Link,
  Modal,
  ModalBody,
  ModalFooter,
  ModalOverlay,
  ModalContent,
  ModalHeader,
  ModalCloseButton,
  FormControl,
  FormLabel,
  Input,
  useDisclosure,
} from "@chakra-ui/react";

import { HashLink } from "react-router-hash-link";
import { useRouter } from "next/router";
const Nav = () => {
  const MenuItem = ({ children, isLast, to = "/", ...rest }) => {
    return (
      <Flex {...rest}>
        <HashLink to={to} smooth>
          <Flex p="1.25rem" variant="ghost" fontSize="lg" fontWeight="500">
            <Text color="#fff" display="flex">
              {children}
            </Text>
          </Flex>
        </HashLink>
      </Flex>
    );
  };
  const router = useRouter();
  const { isOpen, onOpen, onClose } = useDisclosure();
  const initialRef = React.useRef(null);
  const finalRef = React.useRef(null);
  return (
    <>
      <Flex
        className="nav"
        align="center"
        w="100%"
        height="11vh"
        position="sticky"
        justify="space-around"
        top="0"
        zIndex="50"
        background="rgba(10,22,57)">
        <Link
          onClick={() => {
            router.push("/");
          }}>
          <Flex id="StickyLogo">
            <Heading fontSize="2rem" fontWeight="800" color="#fff">
              Getgoing_
            </Heading>
          </Flex>
        </Link>

        <Flex className="NavComponents" align="left" flexBasis="auto">
          <Flex align="center" justify="flex-end" position="relative">
            <MenuItem position="relative" to="#top">
              Home
            </MenuItem>
            <MenuItem position="relative" to="#aboutUs">
              About Us
            </MenuItem>
            <MenuItem position="relative" to="#signup">
              Sign Up
            </MenuItem>
            <MenuItem position="relative" to="#contactus">
              Contact Us
            </MenuItem>
          </Flex>
        </Flex>
        <Flex className="NavComponents2" align="left">
          <Flex p="1.25rem">
            <Button size="md" colorScheme="blackAlpha" onClick={onOpen}>
              Take a Peek
            </Button>
            <Modal
              className="Free"
              initialFocusRef={initialRef}
              finalFocusRef={finalRef}
              isOpen={isOpen}
              onClose={onClose}>
              <ModalOverlay />
              <ModalContent>
                <ModalHeader>Sign in</ModalHeader>
                <ModalCloseButton />
                <ModalBody pb={6}>
                  <FormControl>
                    <FormLabel>Email</FormLabel>
                    <Input ref={initialRef} placeholder="Email" />
                  </FormControl>
                  <FormControl mt={4}>
                    <FormLabel>Password</FormLabel>
                    <Input placeholder="Password" type="password" />
                  </FormControl>
                </ModalBody>
                <ModalFooter>
                  <Button
                    colorScheme="blue"
                    mr={3}
                    onClick={() => {
                      router.push("/member");
                    }}>
                    Sign In
                  </Button>
                  <Button onClick={onClose}>Cancel</Button>
                </ModalFooter>
              </ModalContent>
            </Modal>
          </Flex>

          <Flex p="1.25rem">
            <Button size="md" colorScheme="blackAlpha" onClick={onOpen}>
              Sign In
            </Button>
            <Modal
              className="Premium"
              initialFocusRef={initialRef}
              finalFocusRef={finalRef}
              isOpen={isOpen}
              onClose={onClose}>
              <ModalOverlay />
              <ModalContent>
                <ModalHeader>Sign in</ModalHeader>
                <ModalCloseButton />
                <ModalBody pb={6}>
                  <FormControl>
                    <FormLabel>Email</FormLabel>
                    <Input ref={initialRef} placeholder="Email" />
                  </FormControl>
                  <FormControl mt={4}>
                    <FormLabel>Password</FormLabel>
                    <Input placeholder="Password" type="password" />
                  </FormControl>
                </ModalBody>
                <ModalFooter>
                  <Button
                    colorScheme="blue"
                    mr={3}
                    onClick={() => {
                      router.push("/member");
                    }}>
                    Sign In
                  </Button>

                  <Button onClick={onClose}>Cancel</Button>
                </ModalFooter>
              </ModalContent>
            </Modal>
          </Flex>
        </Flex>
      </Flex>
    </>
  );
};

export default Nav;
