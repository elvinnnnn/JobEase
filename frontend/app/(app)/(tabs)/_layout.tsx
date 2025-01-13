import { Redirect, Tabs, useRouter } from "expo-router";
import React, { useEffect } from "react";
import { Platform, StyleSheet, View } from "react-native";
import { HapticTab } from "@/components/HapticTab";
import TabBarBackground from "@/components/ui/TabBarBackground";
import { Colors } from "@/constants/Colors";
import { useColorScheme } from "@/hooks/useColorScheme";
import Ionicons from "@expo/vector-icons/Ionicons";
import { useSession } from "../../ctx";

export default function TabLayout() {
  const router = useRouter();
  const colorScheme = useColorScheme();
  const { session } = useSession();

  useEffect(() => {
    if (!session) {
      router.replace("/auth");
    }
  }, [session, router]);

  return (
    <Tabs
      screenOptions={{
        tabBarActiveTintColor: Colors[colorScheme ?? "light"].tint,
        // headerShown: false,
        tabBarButton: HapticTab,
        tabBarBackground: TabBarBackground,
        tabBarStyle: Platform.select({
          ios: {
            // Use a transparent background on iOS to show the blur effect
            position: "absolute",
          },
          default: {},
        }),
      }}
    >
      <Tabs.Screen
        name="index"
        options={{
          title: "Listings",
          tabBarIcon: ({ color }) => (
            <Ionicons name="briefcase" size={28} color={color} />
          ),
        }}
      />
      <Tabs.Screen
        name="preferences"
        options={{
          title: "Preferences",
          tabBarIcon: ({ color }) => (
            <Ionicons name="person" size={28} color={color} />
          ),
        }}
      />
    </Tabs>
  );
}
