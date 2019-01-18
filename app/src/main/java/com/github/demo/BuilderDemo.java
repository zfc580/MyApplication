package com.github.demo;

public class BuilderDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 构建器
		Builder builder = new ApplePCBuilder();
		// Director
		Director pcDirector = new Director(builder);
		// 封装构建过程, 4核, 内存2GB, Mac系统
		pcDirector.construct(4, 2, "Mac OS X 10.9.1");
		// 构建电脑, 输出相关信息
		System.out.println("Computer Info : " + builder.create().toString());
	}

}

/**
 * Computer产品抽象类, 为了例子简单, 只列出这几个属性
 *
 * @author mrsimple
 *
 */
abstract class Computer {

	protected int mCpuCore = 1;
	protected int mRamSize = 0;
	protected String mOs = "Dos";

	protected Computer() {

	}

	// 设置CPU核心数
	public abstract void setCPU(int core);

	// 设置内存
	public abstract void setRAM(int gb);

	// 设置操作系统
	public abstract void setOs(String os);

	@Override
	public String toString() {
		return "Computer [mCpuCore=" + mCpuCore + ", mRamSize=" + mRamSize + ", mOs=" + mOs + "]";
	}
}

/**
 * Apple电脑
 */
class AppleComputer extends Computer {

	protected AppleComputer() {

	}

	@Override
	public void setCPU(int core) {
		mCpuCore = core;
	}

	@Override
	public void setRAM(int gb) {
		mRamSize = gb;
	}

	@Override
	public void setOs(String os) {
		mOs = os;
	}
}

/**
 * builder抽象类
 *
 */
abstract class Builder {
	// 设置CPU核心数
	public abstract void buildCPU(int core);

	// 设置内存
	public abstract void buildRAM(int gb);

	// 设置操作系统
	public abstract void buildOs(String os);

	// 创建Computer
	public abstract Computer create();

}

class ApplePCBuilder extends Builder {
	private Computer mApplePc = new AppleComputer();

	@Override
	public void buildCPU(int core) {
		mApplePc.setCPU(core);
	}

	@Override
	public void buildRAM(int gb) {
		mApplePc.setRAM(gb);
	}

	@Override
	public void buildOs(String os) {
		mApplePc.setOs(os);
	}

	@Override
	public Computer create() {
		return mApplePc;
	}
}

class Director {
	Builder mBuilder = null;

	/**
	 * 
	 * @param builder
	 */
	public Director(Builder builder) {
		mBuilder = builder;
	}

	/**
	 * 构建对象
	 * 
	 * @param cpu
	 * @param ram
	 * @param os
	 */
	public void construct(int cpu, int ram, String os) {
		mBuilder.buildCPU(cpu);
		mBuilder.buildRAM(ram);
		mBuilder.buildOs(os);
	}
}
