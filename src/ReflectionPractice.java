import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringJoiner;

public class ReflectionPractice {

    public static void main(String[] args) {
        TestClass testClass = new TestClass();
//        invokeMethods(testClass);
//        invokeMethods(testClass);
//        invokeMethods(testClass);

//        showFinal(testClass);

//        showParents(TestClass.class);
//        ArrayList arrayList = new ArrayList(5);
//        showParents(ArrayList.class);
//        showParents(HashMap.class);

        setNull(testClass);
        setNull(testClass);
    }

    // Метод принимает object и вызывает у него все методы без параметров//
    static void invokeMethods(Object name) {
        Class<?> clazz = name.getClass();
        for (Method declaredMethod : clazz.getDeclaredMethods()) {
            String methodName = declaredMethod.getName();
            try {
                declaredMethod.setAccessible(true);
                System.out.format("Invoking method [%s]...", methodName);
                Object o = declaredMethod.invoke(name, (Object[]) null);
                System.out.format("returned: [%s]%n", o);
                declaredMethod.setAccessible(false);
            } catch (Exception x) {
                Throwable cause = x.getCause();
                System.err.format("invocation of [%s] failed: %s%n", methodName, cause.getMessage());
            }
        }
    }

    // Метод принимает object и выводит на экран все сигнатуры методов в ко//торых есть final
    static void showFinal(Object name) {
        Class<?> clazz = name.getClass();
        for (Method declaredMethod : clazz.getDeclaredMethods()) {
            System.out.print("Method [" + declaredMethod.getName() + "] is ");
            if ((declaredMethod.getModifiers() & Modifier.FINAL) == Modifier.FINAL) {
                System.out.println("FINAL");
            } else {
                System.out.println("NOT Final");
            }
        }
    }

    // Метод принимает Class и возвращает список всех предков класса и все интерфейсы которое класс имплементирует
    static void showParents(Class<?> clazz) {
        Class<?> superClass = clazz.getSuperclass();
        System.out.println("Super class of " + clazz.getName() + " is: " + superClass.getName());
        Class<?>[] interfaces = clazz.getInterfaces();
        StringJoiner stringJoiner = new StringJoiner(", ");
        for (Class<?> anInterface : interfaces) {
            stringJoiner.add(anInterface.getName());
        }
        System.out.println("Implements interfaces: [" + stringJoiner + "]");
    }

    // Метод принимает объект и меняет всего его поля на их нулевые значение (null, 0, false etc)+
    static void setNull(Object name) {
        Class<?> clazz = name.getClass();
        try {
            for (Field declaredField : clazz.getDeclaredFields()) {
                String fieldType = declaredField.getGenericType().getTypeName();
                System.out.print("Object [" + clazz.getName() + "], field [" + declaredField.getName() + "], type [" + fieldType + "], value [" + declaredField.get(name) + "]");
                declaredField.setAccessible(true);

                if (fieldType.equals("int")) {
                    declaredField.set(name, 0);
                    System.out.println(", new value [0]");
                } else if (fieldType.equals("boolean")) {
                    declaredField.set(name, false);
                    System.out.println(", new value [false]");
                } else if (fieldType.equals("java.lang.String")) {
                    declaredField.set(name, "");
                    System.out.println(", new value []");
                } else {
                    declaredField.set(name, null);
                    System.out.println(", new value [null]");
                }

                declaredField.setAccessible(false);
            }
        } catch (
                IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    static class TestClass {
        private int ID = 99;
        private String name = "ABC";
        boolean aBoolean = true;


        public void setID() {
            this.ID += 1;
        }

        private void setName() {
            this.name += "A";
        }

        public final int getID() {
            return ID;
        }

        public String getName() {
            return name;
        }
    }

}

