package org.rapidpm.publication.jaxenter.typedconstructor;

/**
 *
 */
public class Main {


  public interface Service_A {
    String doWork_A();
  }

  public static class Service_A_Impl_A implements Service_A {
    @Override
    public String doWork_A() {
      return null;
    }
  }

  public static class Service_A_Impl_B implements Service_A {
    @Override
    public String doWork_A() {
      return null;
    }
  }


  public interface Service_B {
    String doWork_B();
  }

  public static class Service_B_Impl_A implements Service_B {
    @Override
    public String doWork_B() {
      return null;
    }
  }

  public static class Service_B_Impl_B implements Service_B {
    @Override
    public String doWork_B() {
      return null;
    }
  }


  //the only valid combinations
  // Service_A_Impl_A && Service_B_Impl_A
  // Service_A_Impl_B && Service_B_Impl_B

  // not allowed
  // Service_A_Impl_A && Service_B_Impl_B
  // Service_A_Impl_B && Service_B_Impl_A


  //not nice
  public static class DataHolder_AB {
    private String a;
    private String b;

    //not secure
    public DataHolder_AB(final Service_A service_a, final Service_B service_b) {
      a = service_a.doWork_A();
      b = service_b.doWork_B();
    }

    //not secure
    public DataHolder_AB(String a, String b) {
      this.a = a;
      this.b = b;
    }

    //not nice
    public DataHolder_AB(final Service_A_Impl_A service_a, final Service_B_Impl_A service_b) {
      a = service_a.doWork_A();
      b = service_b.doWork_B();
    }

    //not nice
    public DataHolder_AB(final Service_A_Impl_B service_a, final Service_B_Impl_B service_b) {
      a = service_a.doWork_A();
      b = service_b.doWork_B();
    }
  }


  //with generics
  public static class DataHolder {

    //not secure
    //public <A extends Service_A, B extends Service_B> DataHolder(A serviceA, B serviceB) {}

    //ok
    public <A extends Service_A_Impl_A, B extends Service_B_Impl_A> DataHolder(A serviceA, B serviceB) {}
    public <A extends Service_A_Impl_B, B extends Service_B_Impl_B> DataHolder(A serviceA, B serviceB) {}

  }

  public static void main(String[] args) {

    new DataHolder(new Service_A_Impl_A(), new Service_B_Impl_A());
    //break ;-)
    //new DataHolder(new Service_A_Impl_A(), new Service_B_Impl_B());
  }

}
