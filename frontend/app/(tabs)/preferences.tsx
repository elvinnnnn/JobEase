import { StyleSheet, Image, Platform } from "react-native";

import { Collapsible } from "@/components/Collapsible";
import { ExternalLink } from "@/components/ExternalLink";
import { ThemedText } from "@/components/ThemedText";
import { ThemedView } from "@/components/ThemedView";
import { IconSymbol } from "@/components/ui/IconSymbol";

export default function TabTwoScreen() {
  return (
    <ThemedView>
      <ThemedText>Upload Resume</ThemedText>
      <ThemedText>Job Type</ThemedText>
      <ThemedText>Location</ThemedText>
      <ThemedText>Experience Level</ThemedText>
      <ThemedText>Remote</ThemedText>
      <ThemedText>Industry</ThemedText>
      <ThemedText>Salary</ThemedText>
    </ThemedView>
  );
}

const styles = StyleSheet.create({
  headerImage: {
    color: "#808080",
    bottom: -90,
    left: -35,
    position: "absolute",
  },
  titleContainer: {
    flexDirection: "row",
    gap: 8,
  },
});
