package com.goodboi.activities.data;

import java.util.ArrayList;

public class Test {
    ArrayList<Dog> dogs = new ArrayList<Dog>();
    int nombre = 0;

    public Test() {
        int i=0;
        dogs.add(new Dog(i++,"https://images.dog.ceo/breeds/retriever-golden/n02099601_7807.jpg"));
        dogs.add(new Dog(i++,"https://images.dog.ceo/breeds/terrier-scottish/n02097298_2998.jpg"));
        dogs.add(new Dog(i++,"https://images.dog.ceo/breeds/pomeranian/n02112018_5091.jpg"));
        dogs.add(new Dog(i++,"https://images.dog.ceo/breeds/poodle-miniature/n02113712_3171.jpg"));
        dogs.add(new Dog(i++,"https://images.dog.ceo/breeds/dingo/n02115641_1380.jpg"));
        dogs.add(new Dog(i++,"https://images.dog.ceo/breeds/malamute/n02110063_2481.jpg"));
        dogs.add(new Dog(i++,"https://images.dog.ceo/breeds/labrador/n02099712_7133.jpg"));
        dogs.add(new Dog(i++,"https://images.dog.ceo/breeds/appenzeller/n02107908_3991.jpg"));
        dogs.add(new Dog(i++,"https://images.dog.ceo/breeds/akita/512px-Akita_inu.jpeg"));
        dogs.add(new Dog(i++,"https://images.dog.ceo/breeds/african/n02116738_4367.jpg"));

    }
}
