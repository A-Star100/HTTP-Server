package com.google.appinventor.components.runtime.multidex;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import dalvik.system.DexFile;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipFile;

public final class MultiDex {
    private static final boolean IS_VM_MULTIDEX_CAPABLE = isVMMultidexCapable(System.getProperty("java.vm.version"));
    private static final int MAX_SUPPORTED_SDK_VERSION = 20;
    private static final int MIN_SDK_VERSION = 4;
    private static final String OLD_SECONDARY_FOLDER_NAME = "secondary-dexes";
    private static final String SECONDARY_FOLDER_NAME = ("code_cache" + File.separator + OLD_SECONDARY_FOLDER_NAME);
    static final String TAG = "MultiDex";
    private static final int VM_WITH_MULTIDEX_VERSION_MAJOR = 2;
    private static final int VM_WITH_MULTIDEX_VERSION_MINOR = 1;
    private static final Set<String> installedApk = new HashSet();

    private MultiDex() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00df, code lost:
        android.util.Log.i(TAG, "install done");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00e7, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean install(android.content.Context r9, boolean r10) {
        /*
            java.util.Set<java.lang.String> r0 = installedApk
            r0.clear()
            java.lang.String r1 = "MultiDex"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "install: doIt = "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r10)
            java.lang.String r2 = r2.toString()
            android.util.Log.i(r1, r2)
            boolean r1 = IS_VM_MULTIDEX_CAPABLE
            r2 = 1
            if (r1 == 0) goto L_0x002a
            java.lang.String r0 = "MultiDex"
            java.lang.String r1 = "VM has multidex support, MultiDex support library is disabled."
            android.util.Log.i(r0, r1)
            return r2
        L_0x002a:
            android.content.pm.ApplicationInfo r1 = getApplicationInfo(r9)     // Catch:{ Exception -> 0x00fd }
            if (r1 != 0) goto L_0x0039
            java.lang.String r0 = "MultiDex"
            java.lang.String r3 = "applicationInfo is null, returning"
            android.util.Log.d(r0, r3)     // Catch:{ Exception -> 0x00fd }
            return r2
        L_0x0039:
            monitor-enter(r0)     // Catch:{ Exception -> 0x00fd }
            java.lang.String r3 = r1.sourceDir     // Catch:{ all -> 0x00fa }
            boolean r4 = r0.contains(r3)     // Catch:{ all -> 0x00fa }
            if (r4 == 0) goto L_0x0044
            monitor-exit(r0)     // Catch:{ all -> 0x00fa }
            return r2
        L_0x0044:
            r0.add(r3)     // Catch:{ all -> 0x00fa }
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x00fa }
            r5 = 20
            if (r4 <= r5) goto L_0x007d
            java.lang.String r4 = "MultiDex"
            int r5 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x00fa }
            java.lang.String r6 = "java.vm.version"
            java.lang.String r6 = java.lang.System.getProperty(r6)     // Catch:{ all -> 0x00fa }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x00fa }
            r7.<init>()     // Catch:{ all -> 0x00fa }
            java.lang.String r8 = "MultiDex is not guaranteed to work in SDK version "
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ all -> 0x00fa }
            java.lang.StringBuilder r5 = r7.append(r5)     // Catch:{ all -> 0x00fa }
            java.lang.String r7 = ": SDK version higher than 20 should be backed by runtime with built-in multidex capabilty but it's not the case here: java.vm.version=\""
            java.lang.StringBuilder r5 = r5.append(r7)     // Catch:{ all -> 0x00fa }
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ all -> 0x00fa }
            java.lang.String r6 = "\""
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ all -> 0x00fa }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x00fa }
            android.util.Log.w(r4, r5)     // Catch:{ all -> 0x00fa }
        L_0x007d:
            java.lang.ClassLoader r4 = r9.getClassLoader()     // Catch:{ RuntimeException -> 0x00f0 }
            if (r4 != 0) goto L_0x008d
            java.lang.String r5 = "MultiDex"
            java.lang.String r6 = "Context class loader is null. Must be running in test mode. Skip patching."
            android.util.Log.e(r5, r6)     // Catch:{ all -> 0x00fa }
            monitor-exit(r0)     // Catch:{ all -> 0x00fa }
            return r2
        L_0x008d:
            clearOldDexDir(r9)     // Catch:{ all -> 0x0091 }
            goto L_0x0099
        L_0x0091:
            r5 = move-exception
            java.lang.String r6 = "MultiDex"
            java.lang.String r7 = "Something went wrong when trying to clear old MultiDex extraction, continuing without cleaning."
            android.util.Log.w(r6, r7, r5)     // Catch:{ all -> 0x00fa }
        L_0x0099:
            java.io.File r5 = new java.io.File     // Catch:{ all -> 0x00fa }
            java.lang.String r6 = r1.dataDir     // Catch:{ all -> 0x00fa }
            java.lang.String r7 = SECONDARY_FOLDER_NAME     // Catch:{ all -> 0x00fa }
            r5.<init>(r6, r7)     // Catch:{ all -> 0x00fa }
            r6 = 0
            if (r10 != 0) goto L_0x00b4
            boolean r7 = com.google.appinventor.components.runtime.multidex.MultiDexExtractor.mustLoad(r9, r1)     // Catch:{ all -> 0x00fa }
            if (r7 == 0) goto L_0x00b4
            java.lang.String r2 = "MultiDex"
            java.lang.String r7 = "Returning because of mustLoad"
            android.util.Log.d(r2, r7)     // Catch:{ all -> 0x00fa }
            monitor-exit(r0)     // Catch:{ all -> 0x00fa }
            return r6
        L_0x00b4:
            java.lang.String r7 = "MultiDex"
            java.lang.String r8 = "Proceeding with installation..."
            android.util.Log.d(r7, r8)     // Catch:{ all -> 0x00fa }
            java.util.List r6 = com.google.appinventor.components.runtime.multidex.MultiDexExtractor.load(r9, r1, r5, r6)     // Catch:{ all -> 0x00fa }
            boolean r7 = checkValidZipFiles(r6)     // Catch:{ all -> 0x00fa }
            if (r7 == 0) goto L_0x00c9
            installSecondaryDexes(r4, r5, r6)     // Catch:{ all -> 0x00fa }
            goto L_0x00de
        L_0x00c9:
            java.lang.String r7 = "MultiDex"
            java.lang.String r8 = "Files were not valid zip files.  Forcing a reload."
            android.util.Log.w(r7, r8)     // Catch:{ all -> 0x00fa }
            java.util.List r7 = com.google.appinventor.components.runtime.multidex.MultiDexExtractor.load(r9, r1, r5, r2)     // Catch:{ all -> 0x00fa }
            r6 = r7
            boolean r7 = checkValidZipFiles(r6)     // Catch:{ all -> 0x00fa }
            if (r7 == 0) goto L_0x00e8
            installSecondaryDexes(r4, r5, r6)     // Catch:{ all -> 0x00fa }
        L_0x00de:
            monitor-exit(r0)     // Catch:{ all -> 0x00fa }
            java.lang.String r0 = "MultiDex"
            java.lang.String r1 = "install done"
            android.util.Log.i(r0, r1)
            return r2
        L_0x00e8:
            java.lang.RuntimeException r2 = new java.lang.RuntimeException     // Catch:{ all -> 0x00fa }
            java.lang.String r7 = "Zip files were not valid."
            r2.<init>(r7)     // Catch:{ all -> 0x00fa }
            throw r2     // Catch:{ all -> 0x00fa }
        L_0x00f0:
            r4 = move-exception
            java.lang.String r5 = "MultiDex"
            java.lang.String r6 = "Failure while trying to obtain Context class loader. Must be running in test mode. Skip patching."
            android.util.Log.w(r5, r6, r4)     // Catch:{ all -> 0x00fa }
            monitor-exit(r0)     // Catch:{ all -> 0x00fa }
            return r2
        L_0x00fa:
            r2 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00fa }
            throw r2     // Catch:{ Exception -> 0x00fd }
        L_0x00fd:
            r0 = move-exception
            java.lang.String r1 = "MultiDex"
            java.lang.String r2 = "Multidex installation failure"
            android.util.Log.e(r1, r2, r0)
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.String r2 = r0.getMessage()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Multi dex installation failed ("
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r2 = r3.append(r2)
            java.lang.String r3 = ")."
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.multidex.MultiDex.install(android.content.Context, boolean):boolean");
    }

    private static ApplicationInfo getApplicationInfo(Context context) throws PackageManager.NameNotFoundException {
        try {
            PackageManager pm = context.getPackageManager();
            String packageName = context.getPackageName();
            if (pm == null || packageName == null) {
                return null;
            }
            return pm.getApplicationInfo(packageName, 128);
        } catch (RuntimeException e) {
            Log.w(TAG, "Failure while trying to obtain ApplicationInfo from Context. Must be running in test mode. Skip patching.", e);
            return null;
        }
    }

    static boolean isVMMultidexCapable(String versionString) {
        String str;
        boolean isMultidexCapable = false;
        if (versionString != null) {
            Matcher matcher = Pattern.compile("(\\d+)\\.(\\d+)(\\.\\d+)?").matcher(versionString);
            if (matcher.matches()) {
                boolean z = true;
                try {
                    int major = Integer.parseInt(matcher.group(1));
                    int minor = Integer.parseInt(matcher.group(2));
                    if (major <= 2 && (major != 2 || minor < 1)) {
                        z = false;
                    }
                    isMultidexCapable = z;
                } catch (NumberFormatException e) {
                }
            }
        }
        if (isMultidexCapable) {
            str = " has multidex support";
        } else {
            str = " does not have multidex support";
        }
        Log.i(TAG, "VM with version " + versionString + str);
        return isMultidexCapable;
    }

    private static void installSecondaryDexes(ClassLoader loader, File dexDir, List<File> files) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, IOException {
        if (files.isEmpty()) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            V19.install(loader, files, dexDir);
        } else if (Build.VERSION.SDK_INT >= 14) {
            V14.install(loader, files, dexDir);
        } else {
            V4.install(loader, files);
        }
    }

    private static boolean checkValidZipFiles(List<File> files) {
        for (File file : files) {
            if (!MultiDexExtractor.verifyZipFile(file)) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static Field findField(Object instance, String name) throws NoSuchFieldException {
        Class cls = instance.getClass();
        while (cls != null) {
            try {
                Field field = cls.getDeclaredField(name);
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                return field;
            } catch (NoSuchFieldException e) {
                cls = cls.getSuperclass();
            }
        }
        throw new NoSuchFieldException("Field " + name + " not found in " + instance.getClass());
    }

    /* access modifiers changed from: private */
    public static Method findMethod(Object instance, String name, Class<?>... parameterTypes) throws NoSuchMethodException {
        Class cls = instance.getClass();
        while (cls != null) {
            try {
                Method method = cls.getDeclaredMethod(name, parameterTypes);
                if (!method.isAccessible()) {
                    method.setAccessible(true);
                }
                return method;
            } catch (NoSuchMethodException e) {
                cls = cls.getSuperclass();
            }
        }
        List asList = Arrays.asList(parameterTypes);
        throw new NoSuchMethodException("Method " + name + " with parameters " + asList + " not found in " + instance.getClass());
    }

    /* access modifiers changed from: private */
    public static void expandFieldArray(Object instance, String fieldName, Object[] extraElements) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field jlrField = findField(instance, fieldName);
        Object[] original = (Object[]) jlrField.get(instance);
        Object[] combined = (Object[]) Array.newInstance(original.getClass().getComponentType(), original.length + extraElements.length);
        System.arraycopy(original, 0, combined, 0, original.length);
        System.arraycopy(extraElements, 0, combined, original.length, extraElements.length);
        jlrField.set(instance, combined);
    }

    private static void clearOldDexDir(Context context) throws Exception {
        File dexDir = new File(context.getFilesDir(), OLD_SECONDARY_FOLDER_NAME);
        if (dexDir.isDirectory()) {
            Log.i(TAG, "Clearing old secondary dex dir (" + dexDir.getPath() + ").");
            File[] files = dexDir.listFiles();
            if (files == null) {
                Log.w(TAG, "Failed to list secondary dex dir content (" + dexDir.getPath() + ").");
                return;
            }
            for (File oldFile : files) {
                Log.i(TAG, "Trying to delete old file " + oldFile.getPath() + " of size " + oldFile.length());
                if (!oldFile.delete()) {
                    Log.w(TAG, "Failed to delete old file " + oldFile.getPath());
                } else {
                    Log.i(TAG, "Deleted old file " + oldFile.getPath());
                }
            }
            if (!dexDir.delete()) {
                Log.w(TAG, "Failed to delete secondary dex dir " + dexDir.getPath());
                return;
            }
            Log.i(TAG, "Deleted old secondary dex dir " + dexDir.getPath());
        }
    }

    private static final class V19 {
        private V19() {
        }

        /* access modifiers changed from: private */
        public static void install(ClassLoader loader, List<File> additionalClassPathEntries, File optimizedDirectory) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException {
            IOException[] dexElementsSuppressedExceptions;
            Object dexPathList = MultiDex.findField(loader, "pathList").get(loader);
            ArrayList<IOException> suppressedExceptions = new ArrayList<>();
            MultiDex.expandFieldArray(dexPathList, "dexElements", makeDexElements(dexPathList, new ArrayList(additionalClassPathEntries), optimizedDirectory, suppressedExceptions));
            if (suppressedExceptions.size() > 0) {
                Iterator<IOException> it = suppressedExceptions.iterator();
                while (it.hasNext()) {
                    Log.w(MultiDex.TAG, "Exception in makeDexElement", it.next());
                }
                Field suppressedExceptionsField = MultiDex.findField(loader, "dexElementsSuppressedExceptions");
                IOException[] dexElementsSuppressedExceptions2 = (IOException[]) suppressedExceptionsField.get(loader);
                if (dexElementsSuppressedExceptions2 == null) {
                    dexElementsSuppressedExceptions = (IOException[]) suppressedExceptions.toArray(new IOException[suppressedExceptions.size()]);
                } else {
                    IOException[] combined = new IOException[(suppressedExceptions.size() + dexElementsSuppressedExceptions2.length)];
                    suppressedExceptions.toArray(combined);
                    System.arraycopy(dexElementsSuppressedExceptions2, 0, combined, suppressedExceptions.size(), dexElementsSuppressedExceptions2.length);
                    dexElementsSuppressedExceptions = combined;
                }
                suppressedExceptionsField.set(loader, dexElementsSuppressedExceptions);
            }
        }

        private static Object[] makeDexElements(Object dexPathList, ArrayList<File> files, File optimizedDirectory, ArrayList<IOException> suppressedExceptions) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
            return (Object[]) MultiDex.findMethod(dexPathList, "makeDexElements", ArrayList.class, File.class, ArrayList.class).invoke(dexPathList, new Object[]{files, optimizedDirectory, suppressedExceptions});
        }
    }

    private static final class V14 {
        private V14() {
        }

        /* access modifiers changed from: private */
        public static void install(ClassLoader loader, List<File> additionalClassPathEntries, File optimizedDirectory) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException {
            Object dexPathList = MultiDex.findField(loader, "pathList").get(loader);
            MultiDex.expandFieldArray(dexPathList, "dexElements", makeDexElements(dexPathList, new ArrayList(additionalClassPathEntries), optimizedDirectory));
        }

        private static Object[] makeDexElements(Object dexPathList, ArrayList<File> files, File optimizedDirectory) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
            return (Object[]) MultiDex.findMethod(dexPathList, "makeDexElements", ArrayList.class, File.class).invoke(dexPathList, new Object[]{files, optimizedDirectory});
        }
    }

    private static final class V4 {
        private V4() {
        }

        /* access modifiers changed from: private */
        public static void install(ClassLoader loader, List<File> additionalClassPathEntries) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, IOException {
            int extraSize = additionalClassPathEntries.size();
            Field pathField = MultiDex.findField(loader, "path");
            StringBuilder path = new StringBuilder((String) pathField.get(loader));
            String[] extraPaths = new String[extraSize];
            File[] extraFiles = new File[extraSize];
            ZipFile[] extraZips = new ZipFile[extraSize];
            DexFile[] extraDexs = new DexFile[extraSize];
            ListIterator<File> iterator = additionalClassPathEntries.listIterator();
            while (iterator.hasNext()) {
                File additionalEntry = iterator.next();
                String entryPath = additionalEntry.getAbsolutePath();
                path.append(':').append(entryPath);
                int index = iterator.previousIndex();
                extraPaths[index] = entryPath;
                extraFiles[index] = additionalEntry;
                extraZips[index] = new ZipFile(additionalEntry);
                extraDexs[index] = DexFile.loadDex(entryPath, entryPath + ".dex", 0);
            }
            pathField.set(loader, path.toString());
            MultiDex.expandFieldArray(loader, "mPaths", extraPaths);
            MultiDex.expandFieldArray(loader, "mFiles", extraFiles);
            MultiDex.expandFieldArray(loader, "mZips", extraZips);
            MultiDex.expandFieldArray(loader, "mDexs", extraDexs);
        }
    }
}
