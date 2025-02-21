import { createStackNavigator } from "@react-navigation/stack";
import HomeScreen from "../screens/home";

const Stack = createStackNavigator();

export default function AppNavigator() {


  return (
    <Stack.Navigator screenOptions={{ headerShown: false }}>
       <Stack.Screen name="Home" component={HomeScreen} />

    </Stack.Navigator>
  );
}
