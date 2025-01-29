import React from "react";
import { useState } from "react";
import { StyleSheet, View, Pressable, Linking, Text } from "react-native";
import Ionicons from "@expo/vector-icons/Ionicons";
import { Href, router } from "expo-router";
import { openBrowserAsync } from "expo-web-browser";
import { Colors } from "@/constants/Colors";

interface Job {
  id: number;
  role: string;
  company: string;
  site: string;
  salary: string;
  location: string;
  tags: string[];
}

export default function JobCard(props: { item: Job }) {
  const [opacity, setOpacity] = useState(1);

  const handlePress = () => {
    Linking.openURL(props.item.site);
  };

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
      onPress={() => openBrowserAsync(props.item.site)}
      style={{ opacity }}
    >
      <View style={styles.card}>
        <View style={styles.header}>
          <Text style={styles.title}>{props.item.role}</Text>
          <Text style={styles.text}>{props.item.location}</Text>
        </View>

        <Text style={styles.company}>{props.item.company}</Text>

        <View style={styles.footer}>
          <View style={styles.tagContainer}>
            {props.item.tags.map((tag, index) => (
              <Text key={index} style={styles.tag}>
                {tag}
              </Text>
            ))}
          </View>
          <Text style={styles.salary}>{props.item.salary}</Text>
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
    justifyContent: "flex-end",
    color: Colors.light.text,
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
  header: {
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
