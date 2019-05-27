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













