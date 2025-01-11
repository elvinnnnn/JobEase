import React from "react";
import { StyleSheet, FlatList, Dimensions } from "react-native";
import { ThemedView } from "@/components/ThemedView";
import JobCard from "../../../components/JobCard";
import { useState } from "react";

export default function HomeScreen() {
  // dummy data until backend is ready
  const [jobs, setJobs] = useState([
    {
      role: "Intern Software Developer",
      company: "Doshi",
      site: "https://www.google.com",
      salary: "$76k-$88k",
      location: "Sydney",
    },
    {
      role: "Junior Data Analyst",
      company: "Louie Vuitton",
      site: "https://www.google.com",
      salary: "$67k-$76k",
      location: "Brisbane",
    },
    {
      role: "Help Desk Level 1",
      company: "GEEZ",
      site: "https://www.google.com",
      salary: "$60k-$70k",
      location: "Melbourne",
    },
    {
      role: "IT Consultant",
      company: "Louie Vuitton",
      site: "https://www.google.com",
      salary: "$81k-$90k",
      location: "Melbourne",
    },
    {
      role: "UI/UX Designer",
      company: "GEEZ",
      site: "https://www.google.com",
      salary: "$88k-$100k",
      location: "Sydney",
    },
  ]);

  return (
    <ThemedView style={styles.container}>
      <FlatList
        style={styles.listContent}
        data={jobs}
        renderItem={({ item }) => (
          <ThemedView>
            <JobCard item={item} />
          </ThemedView>
        )}
      />
    </ThemedView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
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
});
