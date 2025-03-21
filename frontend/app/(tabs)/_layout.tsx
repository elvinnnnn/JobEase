import { Slot, Tabs, useRouter } from "expo-router";
import React, { useEffect } from "react";
import { Platform } from "react-native";
import { HapticTab } from "@/components/HapticTab";
import TabBarBackground from "@/components/ui/TabBarBackground";
import { Colors } from "@/constants/Colors";
import Ionicons from "@expo/vector-icons/Ionicons";
import { useSession } from "@/hooks/context";

export default function TabLayout() {
  const router = useRouter();
  const { session } = useSession();

  useEffect(() => {
    if (!session) {
      router.replace("/");
    }
  }, [session, router]);

  return (
    <Tabs
      screenOptions={{
        tabBarActiveTintColor: Colors.light.tint,
        tabBarButton: HapticTab,
        tabBarBackground: TabBarBackground,
        tabBarStyle: Platform.select({
          ios: {
            position: "absolute",
          },
          default: {},
        }),
      }}
    >
      <Tabs.Screen
        name="listings"
        options={{
          title: "Listings",
          tabBarIcon: ({ color }) => (
            <Ionicons name="briefcase" size={28} color={color} />
          ),
          tabBarShowLabel: false,
        }}
      />
      <Tabs.Screen
        name="preferences"
        options={{
          title: "Preferences",
          tabBarIcon: ({ color }) => (
            <Ionicons name="person" size={28} color={color} />
          ),
          tabBarShowLabel: false,
        }}
      />
    </Tabs>
  );
}
