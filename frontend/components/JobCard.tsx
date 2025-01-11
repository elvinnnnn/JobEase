import React from "react";
import { useState } from "react";
import { StyleSheet, View, Pressable, Linking } from "react-native";
import { ThemedText } from "@/components/ThemedText";
import Ionicons from "@expo/vector-icons/Ionicons";

export default function JobCard(props: any) {
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
      onPress={handlePress}
      style={{ opacity }}
    >
      <View style={styles.card}>
        <View style={styles.row}>
          <View style={styles.textContainer}>
            <ThemedText>{props.item.role}</ThemedText>
            <ThemedText>{props.item.company}</ThemedText>
            <ThemedText>{props.item.salary}</ThemedText>
            <ThemedText>{props.item.location}</ThemedText>
          </View>
          <View style={styles.iconContainer}>
            <Ionicons name="chevron-forward" size={28} color={"#fff"} />
          </View>
        </View>
      </View>
    </Pressable>
  );
}

const styles = StyleSheet.create({
  card: {
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
  row: {
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "space-between",
  },
  textContainer: {
    flex: 1,
    alignSelf: "flex-start",
  },
  iconContainer: {
    padding: 10,
  },
});
