import { Pressable, StyleSheet, View, Text, ScrollView } from "react-native";
import { useSession } from "@/hooks/context";
import { Colors } from "@/constants/Colors";
import Selectable from "@/components/Selectable";
import { useSharedValue } from "react-native-reanimated";
import { Slider } from "react-native-awesome-slider";
import React, { useState, useEffect } from "react";
import { GestureHandlerRootView } from "react-native-gesture-handler";
import AsyncStorage from "@react-native-async-storage/async-storage";

export default function PreferencesScreen() {
  const mapping: { [key: string]: string[] } = {
    jobs: ["Full-time", "Part-time", "Contract", "Internship", "Temporary"],
    locations: ["Sydney", "Melbourne", "Brisbane", "Adelaide", "Canberra"],
    experience: ["Entry", "Associate", "Mid-Senior", "Director", "Executive"],
    remote: ["On-site", "Remote", "Hybrid"],
    industry: [
      "Information Technology",
      "Engineering",
      "Finance",
      "Science",
      "Consulting",
      "Trades",
    ],
  };
  const [jobPref, setJobPref] = useState<string[]>([]);
  const [locPref, setLocPref] = useState<string[]>([]);
  const [expPref, setExpPref] = useState<string[]>([]);
  const [remotePref, setRemotePref] = useState<string[]>([]);
  const [industryPref, setIndustryPref] = useState<string[]>([]);
  const { signOut } = useSession();
  const progress = useSharedValue(20);
  const min = useSharedValue(0);
  const max = useSharedValue(50);

  useEffect(() => {
    const getPrefs = async () => {
      try {
        const jsonValue = await AsyncStorage.getItem("prefs");
        if (jsonValue !== null) {
          const prefs = JSON.parse(jsonValue);
          setJobPref(prefs["jobs"]);
          setLocPref(prefs["locations"]);
          setExpPref(prefs["experience"]);
          setRemotePref(prefs["remote"]);
          setIndustryPref(prefs["industry"]);
          progress.value = prefs["distance"];
        }
      } catch (e) {
        console.log("Error occurred when retrieving the local storage");
      }
    };
    getPrefs();
    console.log(jobPref.includes(mapping.jobs[0]));
  }, []);

  const storePrefs = async () => {
    try {
      const prefs: { [key: string]: string[] | number } = {};
      prefs["jobs"] = jobPref;
      prefs["locations"] = locPref;
      prefs["experience"] = expPref;
      prefs["remote"] = remotePref;
      prefs["industry"] = industryPref;
      prefs["distance"] = progress.value;
      const jsonValue = JSON.stringify(prefs);
      console.log(jsonValue);
      await AsyncStorage.setItem("prefs", jsonValue);
    } catch (e) {
      console.log("Error occurred when saving the local storage");
    }
  };

  const updatePref = (pref: string, type: string) => {
    switch (pref) {
      case "job":
        setJobPref((prev) =>
          prev.includes(type)
            ? prev.filter((item) => item !== type)
            : [...prev, type]
        );
        break;
      case "location":
        setLocPref((prev) =>
          prev.includes(type)
            ? prev.filter((item) => item !== type)
            : [...prev, type]
        );
        break;
      case "experience":
        setExpPref((prev) =>
          prev.includes(type)
            ? prev.filter((item) => item !== type)
            : [...prev, type]
        );
        break;
      case "remote":
        setRemotePref((prev) =>
          prev.includes(type)
            ? prev.filter((item) => item !== type)
            : [...prev, type]
        );
        break;
      case "industry":
        setIndustryPref((prev) =>
          prev.includes(type)
            ? prev.filter((item) => item !== type)
            : [...prev, type]
        );
        break;
      default:
        break;
    }
  };

  return (
    <GestureHandlerRootView style={{ flex: 1 }}>
      <View style={styles.container}>
        <Pressable
          style={styles.button}
          onPress={() => {
            signOut();
          }}
        >
          <Text style={styles.buttonText}>Sign Out</Text>
        </Pressable>
        {/* <Pressable
          style={styles.button}
          onPress={() => {
            AsyncStorage.clear();
          }}
        >
          <Text style={styles.buttonText}>Clear Cache</Text>
        </Pressable> */}
        <View style={styles.preferenceContainer}>
          <Text style={styles.text}>Job Type</Text>
          <ScrollView
            horizontal={true}
            showsHorizontalScrollIndicator={false}
            style={styles.optionsContainer}
          >
            {mapping["jobs"].map((item, index) => (
              <Selectable
                key={index}
                onSelect={() => updatePref("job", item)}
                text={item}
                initial={jobPref.includes(item)}
              />
            ))}
          </ScrollView>
        </View>

        <View style={styles.preferenceContainer}>
          <Text style={styles.text}>Location</Text>
          <View style={styles.optionsContainer}>
            {mapping["locations"].map((item, index) =>
              locPref.includes(item) ? (
                <Selectable
                  key={index}
                  onSelect={() => updatePref("location", item)}
                  text={item}
                  initial={true}
                />
              ) : (
                <Selectable
                  key={index}
                  onSelect={() => updatePref("location", item)}
                  text={item}
                  initial={false}
                />
              )
            )}
          </View>
        </View>

        <View style={styles.preferenceContainer}>
          <Text style={styles.text}>Experience Level</Text>
          <View style={styles.optionsContainer}>
            {mapping["experience"].map((item, index) =>
              expPref.includes(item) ? (
                <Selectable
                  key={index}
                  onSelect={() => updatePref("experience", item)}
                  text={item}
                  initial={true}
                />
              ) : (
                <Selectable
                  key={index}
                  onSelect={() => updatePref("experience", item)}
                  text={item}
                  initial={false}
                />
              )
            )}
          </View>
        </View>

        <View style={styles.preferenceContainer}>
          <Text style={styles.text}>Remote</Text>
          <View style={styles.optionsContainer}>
            {mapping["remote"].map((item, index) =>
              remotePref.includes(item) ? (
                <Selectable
                  key={index}
                  onSelect={() => updatePref("remote", item)}
                  text={item}
                  initial={true}
                />
              ) : (
                <Selectable
                  key={index}
                  onSelect={() => updatePref("remote", item)}
                  text={item}
                  initial={false}
                />
              )
            )}
          </View>
        </View>

        <View style={styles.preferenceContainer}>
          <Text style={styles.text}>Industry</Text>
          <ScrollView
            showsHorizontalScrollIndicator={false}
            horizontal={true}
            style={styles.optionsContainer}
          >
            {mapping["industry"].map((item, index) =>
              industryPref.includes(item) ? (
                <Selectable
                  key={index}
                  onSelect={() => updatePref("industry", item)}
                  text={item}
                  initial={true}
                />
              ) : (
                <Selectable
                  key={index}
                  onSelect={() => updatePref("industry", item)}
                  text={item}
                  initial={false}
                />
              )
            )}
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
        <Pressable style={styles.button} onPress={storePrefs}>
          <Text style={styles.buttonText}>Save Changes</Text>
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
