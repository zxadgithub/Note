"[Toc]"
#### @HotSpotIntrinsicCandidate注解
* 可标注于方法，构造函数
* 被标注的方法或者构造函数将在HotSpot中有高效的实现--基于CPU指令，运行时，HotSpot维护的高效实现将替代JDK中的实现，从而获得更高的效率
#### intrinsic的实现方式
* 独立的桩程序 对目标方法的调用将被桩程序替换，既可以被解释器利用，也可以被编译器应用，如Math类的一些方法
* 特殊的IR节点 对源程序的IR节点将被替换为特殊的IR节点，然后被转换为特殊的CPU指令
#### HotSpot中intrinsic举例

* Unsafe类中的方法；
* StringBuffer和StringBuilder中的方法；
* String类，StringLatin1类，StringUTF16类和Userrray类中的方法；
* 基本类型的包装类、Object类、Math类、System类中的功能性方法、反射UserPI、MethodHandle类中与调用机制相关的方法，压缩，加密方法；

#### clone()
clone 复制一个对象的副本注意事项
例如类
```java
class User implements Cloneable{
	private String name;
	private int age;
	//private Address address; 
}
User a = new User;
User b = (User)a.clone();
```
* 拷贝对象a需要实现cloneable接口，包括User继承的层层父类
* 重写clone()方法为public，父类也需要重写 。
* 只拷贝基础类型和String 该类如有属性为引用数据类型如上Address,需要Address类实现Cloneable接口，重写clone方法，才可以实现Address属性的同步拷贝 否则只是简单的赋值，a.address == b.address 为true,引用的为同一个Address实例;正确写法如下：

```java
class Address implements Cloneable{
	@Override
	public Object clone(){
		super.clone();
	}
}
class User implements Cloneable{
	private String name;
	private int age;
	private Address address;
	
	//get set
	
	@Override
	publc Object clone(){
		User u = (User)super.clone();
		u.setAddress((Address)(u.getAddress().clone()));
		return u;
	}
}


User a = new User;
User b = (User)a.clone();
```
### equals()
非空对象相等关系：
对于任何非空引用x,y,z
* 对称性 x.equals(y)为TRUE  则y.equals(x)为TRUE
* 自反性 x.equals(x)始终未true
* 传递性 x.equals(y) 为true y.equals(z) 为true 则 x.equals(z)
* 一致性 ，不改变属性值况下，x.equals(y)一次为true，则永久为true
* x.equals(null)恒为false
Object类的equals 当且仅当引用为一个对象才相等即 x == y 则 x.equals(y)为true


#### finalize()
* GC时会调用此方法，可以重写此方法做一些特殊的内存清理
     有时当撤消一个对象时，需要完成一些操作。例如，如果一个对象正在处理的是**非Java 资源**，如文件句柄或window字符字体，这时你要确认在一个对象被撤消以前要保证这些资源被释放。为处理这样的状况，Java 提供了被称为收尾（finalization ）的机制。使用该机制你可以定义一些特殊的操作，这些操作在一个对象将要被垃圾回收程序释放时执行。
* 不建议用finalize方法完成“非内存资源”的清理工作，但建议用于：① 清理本地对象(通过JNI创建的对象)；② 作为确保某些非内存资源(如Socket、文件等)释放的一个补充：在finalize方法中显式调用其他资源释放方法。
* 多线程同一个对象不保证可见的同步锁定，如果调用出现异常，则忽略，并且该对象的终结操作将终止。
* 不保证该方法会被及时的执行，而且不保证他们会被执行
* 性能问题，该方法的执行在低优先级的线程中
* 对象再生，若finalize()方法内，将该对象赋值给GC Roots可达的对象引用，该对象再生，但是finalize()只会被GC调用一次。故只能再生一次
* 用户可以手动调用此方法，但是不影响GC的调用
* 未重写finalize()的类，直接被JVM优化回收









