package Assigment2;

interface Func<T> {
    boolean Filter(T param);
}

@FunctionalInterface
interface FuncForInteger{
    boolean Filter(int param);
};
