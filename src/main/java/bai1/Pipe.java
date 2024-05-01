package bai1;

public interface  Pipe<IN, OUT> {
    OUT process(IN input);

//    default <NEW_OUT> Pipe<IN, NEW_OUT> add(Pipe<OUT, NEW_OUT> pipe) {
//        return input -> pipe.process(process(input));
//    }

}
