import React, { useEffect, useState } from "react";
import { View, Text, TextInput, StyleSheet, Pressable } from "react-native";
import { Formik } from "formik";
import * as yup from "yup";
import Ionicons from "@expo/vector-icons/Ionicons";
import { useSession } from "@/hooks/context";
import { router } from "expo-router";

const loginSchema = yup.object().shape({
  email: yup
    .string()
    .email("Please enter a valid email. ")
    .required("Email is required. "),
  password: yup
    .string()
    .min(6, ({ min }) => `Password must be at least ${min} characters. `)
    .required("Password is required. "),
});

export default function Auth() {
  const { signUp, session } = useSession();

  useEffect(() => {
    // This is basic redirect, but jwt token should be checked here
    if (session) {
      router.replace("/(tabs)/listings");
    }
  }, [signUp]);

  const handleSubmit = ({
    email,
    password,
  }: {
    email: string;
    password: string;
  }) => {
    signUp(email, password);
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
        {({
          handleChange,
          handleBlur,
          handleSubmit,
          values,
          errors,
          touched,
        }) => (
          <View>
            <View style={styles.inputContainer}>
              {touched.email && errors.email && (
                <Text style={styles.errorText}>{errors.email}</Text>
              )}
              <Ionicons name="mail-outline" size={25} style={styles.icon} />
              <TextInput
                style={styles.input}
                placeholder="Email"
                keyboardType="email-address"
                onChangeText={handleChange("email")}
                onBlur={handleBlur("email")}
                value={values.email}
              />
            </View>

            <View style={styles.inputContainer}>
              {touched.password && errors.password && (
                <Text style={styles.errorText}>{errors.password}</Text>
              )}
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
                onBlur={handleBlur("password")}
                value={values.password}
              />
            </View>
            <Pressable style={styles.button} onPress={() => handleSubmit()}>
              <Text style={styles.buttonText}>Register</Text>
            </Pressable>
            <Text style={styles.signUp}>
              Already have an account?{" "}
              <Pressable onPress={() => router.replace("/signup")}>
                <Text style={styles.signUpLink}>Log In</Text>
              </Pressable>
            </Text>
          </View>
        )}
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
    position: "absolute",
    color: "red",
    alignSelf: "flex-start",
    marginBottom: 10,
    right: 0,
    bottom: 0,
  },
});
