package com.megaflashgames.budgethelper.ui.injector;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.megaflashgames.budgethelper.annotations.ContentView;
import com.megaflashgames.budgethelper.annotations.InjectView;
import com.megaflashgames.budgethelper.annotations.RowView;
import com.megaflashgames.budgethelper.ui.ViewHolder;
import com.megaflashgames.budgethelper.ui.adapter.BaseItemAdapter;
import com.megaflashgames.budgethelper.ui.fragments.FragmentBase;

import java.lang.reflect.Field;

/**
 * Created by vanyamihova on 04/05/2015.
 */
public class MFGInjector {

    public static void onCreate(Activity activity) {
        Class<?> clazz = activity.getClass();

        if(clazz.isAnnotationPresent(ContentView.class)) {
            ContentView layout = clazz.getAnnotation(ContentView.class);
            int layoutId = layout.value();
            activity.setContentView(layoutId);
        }

        try {
            InjectView(activity, clazz);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * Use to inject view on super classes
     *
     * @param activity
     * @param clazz
     * @return
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    private static Class<?> InjectView(Activity activity, Class<?> clazz) throws IllegalAccessException, IllegalArgumentException {
        if(clazz == null || activity == null || clazz.getSimpleName().equalsIgnoreCase(Activity.class.getSimpleName())) {
            return null;
        }

        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if(field.isAnnotationPresent(InjectView.class)) {
                InjectView inject = field.getAnnotation(InjectView.class);
                field.setAccessible(true);
                int id = inject.value();
                field.set(activity, activity.findViewById(id));
            }
        }

        return InjectView(activity,clazz.getSuperclass());
    }


    /**
     * Fragment inject
     */
    public static View onCreateView(FragmentBase fragment, LayoutInflater inflater, ViewGroup container) {
        Class<?> clazz = fragment.getClass();

        while(!(clazz.isAnnotationPresent(ContentView.class) || clazz.getSimpleName().equalsIgnoreCase(FragmentBase.class.getSimpleName()))) {
            clazz = clazz.getSuperclass();
        }

        if(clazz.isAnnotationPresent(ContentView.class)) {
            ContentView layout = clazz.getAnnotation(ContentView.class);
            int layoutId = layout.value();
            return inflater.inflate(layoutId, container, false);
        }
        return null;
    }

    public static void onViewCreated(FragmentBase fragment) {
        Class<?> clazz = fragment.getClass();
        try {
            InjectOnViewCreated(fragment, clazz);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return;
    }

    /**
     * Use to inject view on super classes
     *
     * @param  activity
     * @param  clazz
     * @return
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    private static Class<?> InjectOnViewCreated(FragmentBase fragment, Class<?> clazz) throws IllegalAccessException, IllegalArgumentException {
        if(clazz == null || fragment == null || clazz.getSimpleName().equalsIgnoreCase(FragmentBase.class.getSimpleName())){
            return null;
        }

        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields) {
            if(field.isAnnotationPresent(InjectView.class)) {
                InjectView inject = field.getAnnotation(InjectView.class);
                field.setAccessible(true);
                int id = inject.value();
                field.set(fragment, fragment.getView().findViewById(id));
            }
        }

        return InjectOnViewCreated(fragment, clazz.getSuperclass());
    }


    /**
     * Adapter injector and inflater
     */
    public static View inflate(BaseItemAdapter<?> adapter, LayoutInflater inflater, ViewGroup parent, ViewHolder holder) {
        Class<?> clazz = adapter.getClass();
        View convertView = null;
        if(clazz.isAnnotationPresent(RowView.class)) {
            RowView rowView = clazz.getAnnotation(RowView.class);
            int layoutId = rowView.value();
            if(layoutId <= 0) {
                throw new IllegalArgumentException(String.format("Use should annotate class %s with valid value, not with %d", RowView.class.getSimpleName(),
                        layoutId));
            }

            convertView = inflater.inflate(layoutId, parent, false);

            Class<?> clazzHolder = holder.getClass();
            Field[] fields = clazzHolder.getDeclaredFields();
            try {
                for (Field field : fields) {
                    if (field.isAnnotationPresent(InjectView.class)) {
                        InjectView inject = field.getAnnotation(InjectView.class);
                        field.setAccessible(true);
                        int id = inject.value();
                        field.set(holder, convertView.findViewById(id));
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }

        return convertView;
    }

}
