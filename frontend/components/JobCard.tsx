import React from "react";
import { useState } from "react";
import { StyleSheet, View, Pressable, Linking, Text } from "react-native";
import { openBrowserAsync } from "expo-web-browser";
import { Colors } from "@/constants/Colors";

interface Job {
  id: number;
  jobTitle: string;
  companyName: string;
  jobUrl: string;
  salaryRange: string;
  location: string;
  tags: string[];
}

export default function JobCard(props: { item: Job }) {
  const [opacity, setOpacity] = useState(1);

  const handlePressIn = () => {
    setOpacity(0.8);
  };

  const handlePressOut = () => {
    setOpacity(1);
  };

  return (
    <Pressable
      onPressIn={handlePressIn}
      onPressOut={handlePressOut}
      onPress={() => openBrowserAsync(props.item.jobUrl)}
      style={{ opacity }}
    >
      <View style={styles.card}>
        <View>
          <Text style={styles.title}>{props.item.jobTitle}</Text>
          <Text style={styles.company}>{props.item.companyName}</Text>
        </View>
        <View style={styles.footer}>
          <Text style={styles.salary}>
            {props.item.salaryRange ? props.item.salaryRange : "Unknown Salary"}
          </Text>
          <Text style={styles.text}>{props.item.location}</Text>
        </View>
      </View>
    </Pressable>
  );
}

const styles = StyleSheet.create({
  card: {
    display: "flex",
    borderRadius: 6,
    elevation: 3,
    backgroundColor: "#383838",
    shadowOffset: { width: 1, height: 1 },
    shadowColor: "#333",
    shadowOpacity: 0.3,
    shadowRadius: 2,
    marginHorizontal: 4,
    marginVertical: 6,
    padding: 10,
    height: 150,
    justifyContent: "space-between",
  },
  title: {
    fontSize: 18,
    color: Colors.light.text,
    fontWeight: "bold",
    paddingBottom: 8,
  },
  text: {
    fontSize: 16,
    color: Colors.light.text,
    paddingBottom: 8,
  },
  salary: {
    backgroundColor: Colors.light.text,
    padding: 5,
    marginRight: 8,
    borderRadius: 12,
  },
  tagContainer: {
    display: "flex",
    flexDirection: "row",
  },
  tag: {
    backgroundColor: Colors.light.text,
    padding: 5,
    marginRight: 8,
    borderRadius: 12,
  },
  footer: {
    display: "flex",
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center",
  },
  company: {
    fontSize: 16,
    color: Colors.light.text,
    paddingBottom: 12,
  },
});
