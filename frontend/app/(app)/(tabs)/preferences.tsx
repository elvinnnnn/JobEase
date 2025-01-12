import { StyleSheet } from "react-native";
import { ThemedText } from "@/components/ThemedText";
import { ThemedView } from "@/components/ThemedView";
import { useSession } from "../../ctx";

export default function TabTwoScreen() {
  const { signOut } = useSession();
  return (
    <ThemedView>
      <ThemedText>Upload Resume</ThemedText>
      <ThemedText>Job Type</ThemedText>
      <ThemedText>Location</ThemedText>
      <ThemedText>Experience Level</ThemedText>
      <ThemedText>Remote</ThemedText>
      <ThemedText>Industry</ThemedText>
      <ThemedText>Salary</ThemedText>
      <ThemedText
        style={styles.headerImage}
        onPress={() => {
          // The `app/(app)/_layout.tsx` will redirect to the sign-in screen.
          signOut();
        }}
      >
        Sign Out
      </ThemedText>
    </ThemedView>
  );
}

const styles = StyleSheet.create({
  headerImage: {
    color: "#808080",
    bottom: -90,
    position: "absolute",
  },
  titleContainer: {
    flexDirection: "row",
    gap: 8,
  },
});
