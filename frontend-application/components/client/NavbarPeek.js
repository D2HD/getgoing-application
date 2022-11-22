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
import { BrowserRouter } from "react-router-dom";
import { useRouter } from "next/router";
const Nav = () => {
  const router = useRouter();
  const { isOpen, onOpen, onClose } = useDisclosure();
  const initialRef = React.useRef(null);
  const finalRef = React.useRef(null);
  return (
    <>
      {/* <BrowserRouter> */}
      <Flex
        className="nav"
        align="center"
        w="100%"
        height="11vh"
        position="sticky"
        justify="space-between"
        top="0"
        zIndex="50"
        background="rgba(10,22,57,1)"
        px="70px">
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

        <Flex className="NavComponents2" align="left">
          <Flex p="1.25rem">
            <Link
              onClick={() => {
                router.push("/");
              }}>
              <Button size="md" colorScheme="blackAlpha">
                Sign Out
              </Button>
            </Link>
          </Flex>
          <Flex p="1.25rem">
            <Button size="md" colorScheme="blackAlpha" onClick={onOpen}>
              Premium
            </Button>
            <Modal
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
                  <Link
                    onClick={() => {
                      router.push("/member");
                    }}>
                    <Button colorScheme="blue" mr={3}>
                      Sign In
                    </Button>
                  </Link>
                  <Button onClick={onClose}>Cancel</Button>
                </ModalFooter>
              </ModalContent>
            </Modal>
          </Flex>
        </Flex>
      </Flex>
      {/* </BrowserRouter> */}
    </>
  );
};

export default Nav;
