import React from "react";
import { useState } from "react";
import { View, Text, TextInput, StyleSheet, Pressable } from "react-native";
import { Formik } from "formik";
import * as yup from "yup";
import Ionicons from "@expo/vector-icons/Ionicons";
import { useSession } from "../app/ctx";
import { router } from "expo-router";

const loginSchema = yup.object().shape({
  email: yup
    .string()
    .email("Please enter a valid emial")
    .required("Email is required"),
  password: yup
    .string()
    .min(6, ({ min }) => `Password must be at least ${min} characters`)
    .required("Password is required."),
});

export default function Auth() {
  const { signIn } = useSession();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleChange = (name: string) => (text: string) => {
    if (name === "email") {
      setEmail(text);
    }
    if (name === "password") {
      setPassword(text);
    }
  };

  const handleSubmit = () => {
    signIn();
    router.replace("/");
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>JobEase</Text>
      <Formik
        validationSchema={loginSchema}
        initialValues={{ email: "", password: "" }}
        onSubmit={handleSubmit}
      >
        <>
          <View style={styles.inputContainer}>
            <Ionicons name="mail-outline" size={25} style={styles.icon} />
            <TextInput
              style={styles.input}
              placeholder="Email"
              keyboardType="email-address"
              onChangeText={handleChange("email")}
              value={email}
            />
          </View>
          <View style={styles.inputContainer}>
            <Ionicons
              name="lock-closed-outline"
              size={25}
              style={styles.icon}
            />
            <TextInput
              style={styles.input}
              placeholder="Password"
              secureTextEntry
              onChangeText={handleChange("password")}
              value={password}
            />
          </View>
          <Pressable style={styles.button} onPress={handleSubmit}>
            <Text style={styles.buttonText}>Login</Text>
          </Pressable>
          <Text style={styles.signUp}>
            Don't have an account?{" "}
            <Text style={styles.signUpLink}>Sign Up</Text>
          </Text>
        </>
      </Formik>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    backgroundColor: "#151718",
    paddingHorizontal: 20,
  },
  logo: {
    height: 200,
    width: 200,
    resizeMode: "contain",
    marginBottom: 20,
  },
  title: {
    fontSize: 32,
    marginBottom: 40,
    fontWeight: "bold",
    color: "#ECEDEE",
  },
  inputContainer: {
    flexDirection: "row",
    alignItems: "center",
    width: "100%",
    height: 50,
    backgroundColor: "#f1f1f1",
    borderRadius: 8,
    paddingHorizontal: 10,
    marginBottom: 20,
  },
  icon: {
    marginRight: 10,
  },
  input: {
    flex: 1,
    height: "100%",
  },
  button: {
    width: "100%",
    height: 50,
    backgroundColor: "black",
    borderRadius: 8,
    justifyContent: "center",
    alignItems: "center",
    marginBottom: 20,
  },
  buttonText: {
    color: "#fff",
    fontSize: 18,
  },
  signUp: {
    color: "#fff",
  },
  signUpLink: {
    color: "#fff",
    textDecorationLine: "underline",
  },
  errorText: {
    color: "red",
    alignSelf: "flex-start",
    marginBottom: 10,
  },
});
