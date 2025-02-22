import { createStackNavigator } from "@react-navigation/stack";
import HomeScreen from "../screens/home";
import LoginScreen from "../screens/Login";

const Stack = createStackNavigator();

export default function AppNavigator() {


  return (
    <Stack.Navigator screenOptions={{ headerShown: false }}>
       <Stack.Screen name="Login" component={LoginScreen} />

    </Stack.Navigator>
  );
}
