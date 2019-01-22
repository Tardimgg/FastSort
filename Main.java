public class Main {

    public static Input input = new Input();
    public static Output output = new Output();

    public static void main(String[] args) throws Exception {
        
    }

}

class method {

    public static int gcd(int a, int b) { // NOD
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static int lcm(int a, int b) { // NOK
        return a / gcd(a, b) * b;
    }

    public static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    public static void exit() throws Exception {
        Main.output.output.flush();
        Main.input.input.close();
        Main.output.output.close();
    }
}

class FastSort {

    public static void SortWithoutReturn(int[] array, int ShellHeapMergeMyInsertionSort, int length) {
        sort(array, ShellHeapMergeMyInsertionSort, length);
    }

    public static int[] SortWithReturn(int[] array, int ShellHeapMergeMyInsertionSort, int length) {
        sort(array, ShellHeapMergeMyInsertionSort, length);
        return array;
    }

    private static void sort(int[] array, int ShellHeapMergeMyInsertionSort, int length) {
        Random random = new Random();
        if (ShellHeapMergeMyInsertionSort < 0 || ShellHeapMergeMyInsertionSort > 4) {
            ShellHeapMergeMyInsertionSort = random.nextInt(4);
        }
        if (ShellHeapMergeMyInsertionSort == 0) {
            ShellSort(array);
        } else if (ShellHeapMergeMyInsertionSort == 1) {
            HeapSort(array);
        } else if (ShellHeapMergeMyInsertionSort == 2) {
            MergeSort(array, 0, length - 1);
        } else if (ShellHeapMergeMyInsertionSort == 3) {
            StraightMergeSort(array, length);
        } else if (ShellHeapMergeMyInsertionSort == 4) {
            insertionSort(array);
        }
    }

    private static void StraightMergeSort(int[] array, int size) {
        if (size == 0) {
            return;
        }
        int length = (size / 2) + ((size % 2) == 0 ? 0 : 1);
        Integer[][] ZeroBuffer = new Integer[length + length % 2][2];
        Integer[][] FirstBuffer = new Integer[0][0];
        for (int index = 0; index < length; index++) {
            int ArrayIndex = index * 2;
            int NextArrayIndex = index * 2 + 1;
            if (NextArrayIndex < size) {
                if (array[ArrayIndex] > array[NextArrayIndex]) {
                    ZeroBuffer[index][0] = array[NextArrayIndex];
                    ZeroBuffer[index][1] = array[ArrayIndex];
                } else {
                    ZeroBuffer[index][0] = array[ArrayIndex];
                    ZeroBuffer[index][1] = array[NextArrayIndex];
                }
            } else {
                ZeroBuffer[index][0] = array[ArrayIndex];
            }
        }
        boolean position = false;
        int pointer0;
        int pointer;
        int pointer1;
        int number = 4;
        int NewPointer;
        int count;
        Integer[][] NewBuffer;
        Integer[][] OldBuffer;
        length = (size / 4) + ((size % 4) == 0 ? 0 : 1);
        while (true) {
            pointer0 = 0;
            count = (number / 2) - 1;
            if (!position) {
                FirstBuffer = new Integer[length + length % 2][number];
                NewBuffer = FirstBuffer;
                OldBuffer = ZeroBuffer;
            } else {
                ZeroBuffer = new Integer[length + length % 2][number];
                NewBuffer = ZeroBuffer;
                OldBuffer = FirstBuffer;
            }
            for (int i = 0; i < length; i++) {
                pointer = 0;
                pointer1 = 0;
                NewPointer = pointer0 + 1;
                if (length == 1) {
                    for (int g = 0; g < size; g++) {
                        if (pointer > count || OldBuffer[pointer0][pointer] == null) {
                            array[g] = OldBuffer[NewPointer][pointer1];
                            pointer1++;
                        } else if (pointer1 > count || OldBuffer[NewPointer][pointer1] == null) {
                            if (OldBuffer[pointer0][pointer] == null) {
                                continue;
                            }
                            array[g] = OldBuffer[pointer0][pointer];
                            pointer++;
                        } else if (OldBuffer[pointer0][pointer] >= OldBuffer[NewPointer][pointer1]) {
                            array[g] = OldBuffer[NewPointer][pointer1];
                            pointer1++;
                        } else {
                            array[g] = OldBuffer[pointer0][pointer];
                            pointer++;
                        }
                    }
                    return;
                }
                for (int g = 0; g < number; g++) {
                    if (pointer > count || OldBuffer[pointer0][pointer] == null) {
                        if (OldBuffer[NewPointer][pointer1] == null) {
                            continue;
                        }
                        NewBuffer[i][g] = OldBuffer[NewPointer][pointer1];
                        pointer1++;
                    } else if (pointer1 > count || OldBuffer[NewPointer][pointer1] == null) {
                        if (OldBuffer[pointer0][pointer] == null) {
                            continue;
                        }
                        NewBuffer[i][g] = OldBuffer[pointer0][pointer];
                        pointer++;
                    } else if (OldBuffer[pointer0][pointer] >= OldBuffer[NewPointer][pointer1]) {
                        NewBuffer[i][g] = OldBuffer[NewPointer][pointer1];
                        pointer1++;
                    } else {
                        NewBuffer[i][g] = OldBuffer[pointer0][pointer];
                        pointer++;
                    }
                }
                pointer0 += 2;
            }
            position = !position;
            length = length / 2 + (length % 2 == 0 ? 0 : 1);
            number *= 2;

        }
    }

    public static void ShellSort(int[] array) {
        int j;
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                int temp = array[i];
                for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
                    array[j] = array[j - gap];
                }
                array[j] = temp;
            }
        }
    }

    private static void HeapSort(int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--)
            shiftDown(array, i, array.length);
        for (int i = array.length - 1; i > 0; i--) {
            swap(array, 0, i);
            shiftDown(array, 0, i);
        }
    }

    private static void shiftDown(int[] array, int i, int n) {
        int child;
        int tmp;
        for (tmp = array[i]; leftChild(i) < n; i = child) {
            child = leftChild(i);
            if (child != n - 1 && (array[child] < array[child + 1]))
                child++;
            if (tmp < array[child])
                array[i] = array[child];
            else
                break;
        }
        array[i] = tmp;
    }

    private static int leftChild(int i) {
        return 2 * i + 1;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void MergeSort(int[] array, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            MergeSort(array, low, mid);
            MergeSort(array, mid + 1, high);
            merge(array, low, mid, high);
        }
    }

    private static void merge(int[] array, int low, int mid, int high) {
        int n = high - low + 1;
        int[] Temp = new int[n];
        int i = low, j = mid + 1;
        int k = 0;
        while (i <= mid || j <= high) {
            if (i > mid)
                Temp[k++] = array[j++];
            else if (j > high)
                Temp[k++] = array[i++];
            else if (array[i] < array[j])
                Temp[k++] = array[i++];
            else
                Temp[k++] = array[j++];
        }
        for (j = 0; j < n; j++)
            array[low + j] = Temp[j];
    }

    private static void insertionSort(int[] elements) {
        for (int i = 1; i < elements.length; i++) {
            int key = elements[i];
            int j = i - 1;
            while (j >= 0 && key < elements[j]) {
                elements[j + 1] = elements[j];
                j--;
            }
            elements[j + 1] = key;
        }
    }
}

class Input {

    public BufferedReader input;
    public static boolean FileInput = false;

    Input() {
        try {
            input = new BufferedReader(FileInput ? new FileReader("input.txt") : new InputStreamReader(System.in));
        } catch (Exception error) {
        }
    }

    public int ReadInt() throws Exception {
        return Integer.parseInt(input.readLine());
    }

    public long ReadLong() throws Exception {
        return Long.parseLong(input.readLine());
    }

    public String ReadString() throws Exception {
        return input.readLine();
    }

    public int[] ReadArrayInt(String split) throws Exception {
        return Arrays.stream(input.readLine().split(split)).mapToInt(Integer::parseInt).toArray();
    }

    public long[] ReadArrayLong(String split) throws Exception {
        return Arrays.stream(input.readLine().split(split)).mapToLong(Long::parseLong).toArray();
    }

    public String[] ReadArrayString(String split) throws Exception {
        return input.readLine().split(split);
    }
}

class Output {

    public BufferedWriter output;
    public static boolean FileOutput = false;

    Output() {
        try {
            output = new BufferedWriter(FileOutput ? new FileWriter("output.txt") : new OutputStreamWriter(System.out));
        } catch (Exception error) {
        }
    }

    public void WriteArray(int[] array, String split) {
        try {
            int length = array.length;
            for (int index = 0; index < length; index++){
                output.write(Integer.toString(array[index]));
                if (index + 1 != length){
                    output.write(split);
                }
            }
        }catch (Exception error){ }


    }

    public void WriteArray(long[] array, String split) {
        try {
            int length = array.length;
            for (int index = 0; index < length; index++){
                output.write(Long.toString(array[index]));
                if (index + 1 != length){
                    output.write(split);
                }
            }
        }catch (Exception error){ }
    }

    public void WriteArray(String[] array, String split) {
        try {
            int length = array.length;
            for (int index = 0; index < length; index++){
                output.write(array[index]);
                if (index + 1 != length){
                    output.write(split);
                }
            }
        }catch (Exception error){ }
    }

    public void WriteInt(int number, String split) {
        try {
            output.write(Integer.toString(number));
            output.write(split);
        } catch (Exception error) {
        }
    }
    public void WriteString(String number, String split) {
        try {
            output.write(number);
            output.write(split);
        } catch (Exception error) {
        }
    }
    public void WriteLong(Long number, String split) {
        try {
            output.write(Long.toString(number));
            output.write(split);
        } catch (Exception error) {
        }
    }
}
