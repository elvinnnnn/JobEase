import { Redirect, Slot } from "expo-router";
import { useSession } from "@/hooks/context";

export default function AppLayout() {
  const { session } = useSession();

  if (!session) {
    return <Redirect href="/signin" />;
  }

  return <Slot />;
}
