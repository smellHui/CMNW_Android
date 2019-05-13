package com.tepia.cmdbsevice.view.alarmstatistics.view.flowlayout;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * Author:xch
 * Date:2019/5/13
 * Description:
 */
public class Iterables {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static <E> void forEach(
            Iterable<? extends E> elements, BiConsumer<Integer, ? super E> action) {
        Objects.requireNonNull(elements);
        Objects.requireNonNull(action);

        int index = 0;
        for (E element : elements) {
            action.accept(index++, element);
        }
    }
}
