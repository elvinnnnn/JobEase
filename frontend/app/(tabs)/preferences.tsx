import { Pressable, StyleSheet, View, Text, ScrollView } from "react-native";
import { useSession } from "@/hooks/context";
import { Colors } from "@/constants/Colors";
import Selectable from "@/components/Selectable";
import { useSharedValue } from "react-native-reanimated";
import { Slider } from "react-native-awesome-slider";
import React, { useState } from "react";
import { GestureHandlerRootView } from "react-native-gesture-handler";

export default function PreferencesScreen() {
  const { signOut } = useSession();
  const [isChecked, setCheck] = useState(false);
  const progress = useSharedValue(20);
  const min = useSharedValue(0);
  const max = useSharedValue(50);

  return (
    <GestureHandlerRootView style={{ flex: 1 }}>
      <View style={styles.container}>
        <Pressable style={styles.button}>
          <Text style={styles.buttonText}>Upload Resume</Text>
        </Pressable>
        <View style={styles.preferenceContainer}>
          <Text style={styles.text}>Job Type</Text>
          <ScrollView
            horizontal={true}
            showsHorizontalScrollIndicator={false}
            style={styles.optionsContainer}
          >
            <Selectable text="Full-time" />
            <Selectable text="Part-time" />
            <Selectable text="Contract" />
            <Selectable text="Internship" />
            <Selectable text="Temporary" />
            <Selectable text="Other" />
          </ScrollView>
        </View>

        <View style={styles.preferenceContainer}>
          <Text style={styles.text}>Location</Text>
          <View style={styles.optionsContainer}>
            <Selectable text="Sydney" />
            <Selectable text="Melbourne" />
            <Selectable text="Brisbane" />
            <Selectable text="Adelaide" />
            <Selectable text="Canberra" />
          </View>
        </View>

        <View style={styles.preferenceContainer}>
          <Text style={styles.text}>Experience Level</Text>
          <View style={styles.optionsContainer}>
            <Selectable text="Entry" />
            <Selectable text="Associate" />
            <Selectable text="Mid-Senior" />
            <Selectable text="Director" />
            <Selectable text="Executive" />
          </View>
        </View>

        <View style={styles.preferenceContainer}>
          <Text style={styles.text}>Remote</Text>
          <View style={styles.optionsContainer}>
            <Selectable text="On-site" />
            <Selectable text="Remote" />
            <Selectable text="Hybrid" />
          </View>
        </View>

        <View style={styles.preferenceContainer}>
          <Text style={styles.text}>Industry</Text>
          <ScrollView
            showsHorizontalScrollIndicator={false}
            horizontal={true}
            style={styles.optionsContainer}
          >
            <Selectable text="Information Technology" />
            <Selectable text="Engineering" />
            <Selectable text="Quality Assurance" />
            <Selectable text="Analyst" />
            <Selectable text="Consulting" />
            <Selectable text="Research" />
          </ScrollView>
        </View>

        <View style={styles.distance}>
          <Text style={styles.text}>Distance</Text>
          <Slider
            style={styles.slider}
            progress={progress}
            minimumValue={min}
            maximumValue={max}
            steps={5}
            forceSnapToStep={true}
            bubbleContainerStyle={{ display: "none" }}
            renderMark={({ index }) => (
              <Text style={styles.mark}>{index * 10}km</Text>
            )}
            markStyle={styles.mark}
          />
        </View>
        <Pressable
          style={styles.button}
          onPress={() => {
            signOut();
          }}
        >
          <Text style={styles.buttonText}>Sign Out</Text>
        </Pressable>
      </View>
    </GestureHandlerRootView>
  );
}

const styles = StyleSheet.create({
  container: {
    display: "flex",
    flexDirection: "column",
    padding: 8,
  },
  button: {
    display: "flex",
    alignItems: "center",
    padding: 10,
    shadowOpacity: 0.3,
    shadowRadius: 2,
    marginHorizontal: 6,
    marginVertical: 6,
    borderRadius: 6,
    elevation: 3,
    backgroundColor: Colors.light.text,
    shadowOffset: { width: 1, height: 1 },
  },
  buttonText: {
    fontWeight: "bold",
    fontSize: 18,
  },
  headerImage: {
    color: "#808080",
    bottom: -90,
    position: "absolute",
  },
  titleContainer: {
    flexDirection: "row",
    gap: 8,
  },
  salary: {
    display: "flex",
    flexDirection: "row",
  },
  text: {
    color: Colors.light.text,
    fontSize: 18,
    padding: 5,
    paddingBottom: 20,
    fontWeight: "bold",
  },
  optionsContainer: {
    display: "flex",
    flexDirection: "row",
  },
  preferenceContainer: {
    marginVertical: 16,
  },
  slider: {
    margin: 20,
  },
  mark: {
    color: Colors.light.text,
    width: 40,
    marginLeft: -14,
    marginTop: -28,
  },
  distance: {
    marginTop: 14,
  },
});
