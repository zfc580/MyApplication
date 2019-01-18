package com.java.thinkinjava;

public class IOTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// File file = new File("e://test/");
		// if (!file.exists()) {
		// boolean is = file.mkdirs();
		// System.out.println("is = " + is);
		// }
		//
		// File mFile = new File("e://test/a.txt");
		// FileOutputStream fos = null;
		// try {
		// if (!mFile.exists()) {
		// mFile.createNewFile();
		// }
		// fos = new FileOutputStream(mFile);
		// String str = "it is a test test...";
		// fos.write(str.getBytes(), 0, str.length());
		// fos.flush();
		//
		// } catch (Exception e) {
		// // TODO: handle exception
		// } finally {
		// try {
		// fos.close();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// if (mFile.exists()) {
		// mFile.delete();
		// }
		double a = 1.123456789;
		a = (double) Math.round(a * 1000000) / 1000000;
		System.out.println(a);

	}

}
