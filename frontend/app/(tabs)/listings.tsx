import React from "react";
import {
  StyleSheet,
  FlatList,
  Dimensions,
  View,
  Pressable,
} from "react-native";
import JobCard from "../../components/JobCard";
import { useState } from "react";
import { Colors } from "@/constants/Colors";
import Ionicons from "@expo/vector-icons/Ionicons";

export default function ListingsScreen() {
  // dummy data until backend is ready
  const [jobs, setJobs] = useState([
    {
      id: 1,
      role: "Intern Software Developer",
      company: "Doshi",
      site: "https://www.google.com",
      salary: "$76k-$88k",
      location: "Sydney",
      tags: ["Part-Time", "Internship"],
    },
    {
      id: 2,
      role: "Junior Data Analyst",
      company: "Louie Vuitton",
      site: "https://www.google.com",
      salary: "$67k-$76k",
      location: "Brisbane",
      tags: ["Contract", "Graduate"],
    },
    {
      id: 3,
      role: "Help Desk Level 1",
      company: "GEEZ",
      site: "https://www.google.com",
      salary: "$60k-$70k",
      location: "Melbourne",
      tags: ["Full-Time", "Permanent"],
    },
    {
      id: 4,
      role: "IT Consultant",
      company: "Louie Vuitton",
      site: "https://www.google.com",
      salary: "$81k-$90k",
      location: "Melbourne",
      tags: ["Full-Time", "Permanent"],
    },
    {
      id: 5,
      role: "UI/UX Designer",
      company: "GEEZ",
      site: "https://www.google.com",
      salary: "$88k-$100k",
      location: "Sydney",
      tags: ["Contract", "Permanent"],
    },
  ]);

  return (
    <View style={styles.container}>
      <Pressable style={styles.button}>
        <Ionicons name="reload" size={32} color="black" />
      </Pressable>
      <FlatList
        style={styles.listContent}
        data={jobs}
        renderItem={({ item }) => (
          <View>
            <JobCard item={item} />
          </View>
        )}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
  },
  titleContainer: {
    flexDirection: "column",
    alignItems: "center",
  },
  listContent: {
    padding: 8,
    width: Dimensions.get("window").width,
    flexGrow: 1,
  },
  button: {
    position: "absolute",
    padding: 16,
    shadowOpacity: 0.3,
    shadowRadius: 2,
    borderRadius: 50,
    elevation: 3,
    backgroundColor: Colors.light.text,
    shadowOffset: { width: 1, height: 1 },
    bottom: 100,
    zIndex: 1,
  },
  buttonText: {
    fontWeight: "bold",
  },
});
