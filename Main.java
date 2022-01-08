package com.company;
import java. util.*;
enum Gender{
    male , female ;
}

class Wing {
    String name;
    Room[] room;

    Wing(String name , Room[] r){
        this.name = name;
        this.room = r;
    }
}

class Room {
    double price;
    boolean isAC;
    int roomNo;
    boolean isOccupied;
    Student s = null;

    Room(int room ,double price , boolean isAC , boolean isOccupied){
        this.price = price;
        this.isAC = isAC;
        this.roomNo = room;
        this.isOccupied = isOccupied;
    }
}


class Department {
    String name;
    Department(String name){
        this.name = name;
    }
}

class Order {
    Student s;
    Food[] farr;
    double total = 0;
    int timeWaiting = 0;

    Order(Student s , Food[] farr){
        this.s = s;
        this.farr = farr;

        for(int i = 0 ; i < farr.length ; i++) {
            total += farr[i].price;
        }
    }
}

class ExpenseBook {
    String desc;
    double price;

    ExpenseBook(String desc , double price){
        this.desc = desc;
        this.price = price;
    }

    public void getInfo() {
        System.out.println(this.desc + "\t" + this.price);
    }
}

class Mesh{
    Food[] f;
    int totalSeat;
    ArrayList<Order> orderArr;

    Mesh(Food[] f , int totalSeat){
        this.f = f;
        orderArr = new ArrayList<Order>();
        this.totalSeat = totalSeat;
    }

    void totalOrderInfo() {
        for(Order o : orderArr) {
            System.out.println("Studet name:- " + o.s.name + "\t Price :- " + o.total);
            System.out.println("Uour Order Item:-");

            for(int i = 0 ; i < o.farr.length ; i++) {
                System.out.println(o.farr[i].name);
            }
            System.out.println("------------------------------------------------------------");
        }
    }

    void orderBytudent(Student s , Food[] arr) {
        int total = 0;
        for(int i = 0 ; i < arr.length ; i++) {
            System.out.println(arr[i].name);
            total += arr[i].price;
        }
        System.out.println(total);
        if(total <= s.pocketMoney) {
            System.out.println("Your Order is Accepted");
            s.pocketMoney -= total;
            Order o1 = new Order(s , arr);
            orderArr.add(o1);
            System.out.println("Your Balance :- " + s.pocketMoney);
            String desc = "Food Name :- " ;
            for(int i = 0 ; i < o1.farr.length ; i++) {
//System.out.print(o1.farr[i].name + "\t");
                desc += "["+o1.farr[i].name+"]" + "\t";
            }
            double tp =  o1.total;
            ExpenseBook e1 = new ExpenseBook(desc , tp);
            s.exp.add(e1);
        }else {
            System.out.println("Pocket money error ");
        }
    }

    void menu(String type) {
        type = type.toLowerCase();
//System.out.println(type);
        if(type.equals("veg")) {
            System.out.println("Veg menu");
            for(int i = 0 ; i <f.length ; i++) {
                if(f[i].isVeg) {
                    System.out.println((i+1)+ " " + f[i].name + "\t Price :- " + f[i].price);
                }
            }
        }
        if(type == "nonveg") {
            System.out.println("Non Veg menu");
            for(int i = 0 ; i <f.length ; i++) {
                if(!f[i].isVeg) {
                    System.out.println((i+1)+ " " + f[i].name + "\t Price :- " + f[i].price);
                }
            }
        }
    }
}

class Food {
    double price;
    String name;
    boolean isVeg;

    Food(double price , String name , boolean isVeg){
        this.price = price;
        this.name = name;
        this.isVeg = isVeg;
    }
}

class Student {
    String name;
    Gender g;
    double pocketMoney;
    Department d;
    Mesh m;
    Hostel h;
    ArrayList<ExpenseBook> exp = new ArrayList<ExpenseBook>();

    Student(String name , int pocketMoney ,Gender g , Department d , Mesh m,Hostel h){
        this.name = name;
        this.pocketMoney = pocketMoney;
        this.g = g;
        this.d = d;
        this.m = m;
        this.h = h;
// ArrayList<ExpenseBook> exp = ;
    }

    public void getExpenseInfo() {
        for(ExpenseBook e : exp) {
            e.getInfo();
            System.out.println("*****************************************************");
        }
        System.out.println("--------------------------------------------------");
    }

    public void getDetailsOfStudent() {
        System.out.println("Name :- " + this.name);
        System.out.println("Gender :- " + this.g.toString());
        System.out.println("Pocket Money :- " + this.pocketMoney);
        System.out.println("Department :- " + this.d.name);
        System.out.println("Hostel :- " + this.h.name);

    }

    public void assignRoom(Student s , boolean isAC) {
        int flag = 0 , flag2 = 0,flag3 = 0;
        double temp=0;
        for(int i = 0 ; i < h.w.length ; i++) {
            for(int j = 0 ; j < h.w[i].room.length ; j++) {
                if(h.w[i].room[j].isOccupied == false) {
                    if(isAC == true) {
                        if(h.w[i].room[j].isAC == true) {
                            flag = 1;
                            flag2 = 1;
                            if(s.pocketMoney >= h.w[i].room[j].price) {
//flag = 1;
                                s.pocketMoney -=  h.w[i].room[j].price;
                                h.w[i].room[j].s = s;
                                h.w[i].room[j].isOccupied = true;
                                System.out.println(s.name + " Assign Room from Wing name "+h.w[i].name + " Room no :- " + h.w[i].room[j].roomNo);
//flag2 = 1;
                                flag3 = 1;
                                String desc = "Room Cost , Room no :- " + h.w[i].room[j].roomNo + "\tWing Name :- " + h.w[i].name;
                                double tp = h.w[i].room[j].price;
                                ExpenseBook e1 = new ExpenseBook(desc , tp);
                                s.exp.add(e1);
                                break;
                            }else {
                                temp = h.w[i].room[j].price;
                            }
                        }
                    }
                    else {
                        if(h.w[i].room[j].isAC == false) {
                            flag = 1;
                            flag2 = 1;
                            if(s.pocketMoney >= h.w[i].room[j].price) {

                                h.w[i].room[j].s = s;
                                s.pocketMoney -=  h.w[i].room[j].price;
                                h.w[i].room[j].isOccupied = true;
                                System.out.println(s.name + " Assign Room from Wing name "+h.w[i].name + " Room no :- " + h.w[i].room[j].roomNo);
                                String desc = "Room Cost , Room no :- " + h.w[i].room[j].roomNo + "\tWing Name :- " + h.w[i].name;
                                double tp = h.w[i].room[j].price;
                                ExpenseBook e1 = new ExpenseBook(desc , tp);
                                s.exp.add(e1);
                                flag3 = 1;
                                break;
                            }
                            else {
                                temp = h.w[i].room[j].price;
                            }
                        }
                    }
                }
            }
            if(flag2 == 1) {
                break;
            }

        }
        if(flag == 0) {
//String roomType = (isAC) ?"Ac" :"Non-Ac";
            System.out.println((isAC) ?"Ac" :"Non-Ac" + " "+"Room Not available");
        }
        if(flag3==0 && flag2 == 1 && flag == 1) {
            System.out.println(s.name +" price of room is " + temp + "you have "+s.pocketMoney +" rupee and you need to have " + (temp - s.pocketMoney) + " more rupees");
        }
    }
}

class Hostel{
    Wing[] w;
    String name;

    Hostel(Wing[] w , String name){
        this.w = w;
        this.name = name;
    }

    public void isRoomAvail() {
        int flag = 0;
        for(int i = 0 ; i < w.length ; i++) {
            for(int j = 0 ; j < w[i].room.length ; j++) {
                if(w[i].room[j].isOccupied == false) {
                    flag = 1;
                    System.out.println("Wing name :- " + w[i].name);
                    System.out.println("Room number :- " + w[i].room[j].roomNo);
                    System.out.println("****************************************************");
                }
            }
        }
        if(flag == 0) {
            System.out.println("Room Not available");
        }
    }

    public void RoomReg() {
        for(int i = 0 ; i < w.length ; i++) {
            for(int j = 0 ; j < w[i].room.length ; j++) {
                if(w[i].room[j].isOccupied == true) {
                    System.out.println("Room no :- " + w[i].room[j].roomNo + " Assign to :- " + w[i].room[j].s.name);
                    System.out.println("****************************************************");
                }
            }
        }
    }

    public void deassignRoom(String name) {
        int flag = 0;
        for(int i = 0 ; i < w.length ; i++) {
            for(int j = 0 ; j < w[i].room.length ; j++) {
                if(w[i].room[j].s != null) {
                    if(w[i].room[j].s.name == name) {
                        w[i].room[j].s = null;
                        flag = 1;
                    }
                }
            }
        }
        if(flag == 1) {
            System.out.println("Checkout clear");
        }else {
            System.out.println("No room assign to the person :- " + name);
        }
    }
}

public class Main {

    public static void main(String []args) {
        Food f1 = new Food(200.0,"food1",true);
        Food f2 = new Food(100.0,"food2",true);
        Food f3 = new Food(70.0,"food3",false);
        Food f4 = new Food(80.0,"food4",false);
        Food[] farr = {f1,f2,f3,f4};
        Mesh mesh = new Mesh(farr , 30);
        Department compDep = new Department("Computer");
        Department itDep = new Department("IT");

        Room r1 = new Room(101 , 1200 , true , false);
        Room r2 = new Room(102 , 1200 , true , false);
        Room r3 = new Room(103 , 900 , false , false);
        Room r4 = new Room(104 , 900 , false, false);

        Room[] rarr1 = {r1,r3};
        Room[] rarr2 = {r2,r4};

        Wing w1 = new Wing("Castle" , rarr1);
        Wing w2 = new Wing("Palace" , rarr2);

        Wing[] warr = {w1,w2};
        Hostel h = new Hostel(warr ,"Main Hostel");

//String name , int pocketMoney ,Gender g , Department d , Mesh m,Hostel h

        Student s1 = new Student("John",5000,Gender.male,compDep,mesh,h);
        Student s2 = new Student("Alice",7000,Gender.male,itDep,mesh,h);

        Student s3 = new Student("Student1",5000,Gender.male,compDep,mesh,h);
        Student s4 = new Student("Student2",7000,Gender.male,itDep,mesh,h);
//
// Student s5 = new Student("Student2",7000,Gender.male,itDep,mesh,h);
//
//s1.getDetailsOfStudent();
// h.isRoomAvail();
        s1.assignRoom(s1 , true); //3800  3500
        s2.assignRoom(s2 , false);
// s3.assignRoom(s3, false);
//s4.assignRoom(s4, false);
//h.deassignRoom("Johnww");

//h.RoomReg();

//double price , String name , boolean isVeg

//mesh.menu("Veg");

        Food[] o = {f1,f4};
        Food[] o2 = {f2,f3};

        mesh.orderBytudent(s1, o);
        mesh.orderBytudent(s2, o2);
        mesh.orderBytudent(s1, o2);

// mesh.totalOrderInfo();

        s1.getExpenseInfo();
    }

    }

