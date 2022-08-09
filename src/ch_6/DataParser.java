package ch_6;

import java.util.List;

interface DataParser<T> {
    void parseData(String input, List<T> output, List<String> errors);
}

// The choices in this case are different:
// List<String> will be non-null, because the callers always need to receive error messages.
// The elements in the list will be nullable, because not every item in the output list will have an associated error message.
// List<String> will be mutable, because the implementing code needs to add elements to it.