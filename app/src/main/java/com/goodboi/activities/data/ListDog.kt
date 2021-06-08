package com.goodboi.activities.data

import java.util.*

class ListDog {

    var dogs = ArrayList<Dog>()
    var nombre = 0

    init {
        //TODO appeller 10 fois makeImageRequest() pour remplacer les URL
        var i = 1
        dogs.add(Dog(i++, "https://images.dog.ceo/breeds/retriever-golden/n02099601_7807.jpg"))
        dogs.add(Dog(i++, "https://images.dog.ceo/breeds/terrier-scottish/n02097298_2998.jpg"))
        dogs.add(Dog(i++, "https://images.dog.ceo/breeds/pomeranian/n02112018_5091.jpg"))
        dogs.add(Dog(i++, "https://images.dog.ceo/breeds/poodle-miniature/n02113712_3171.jpg"))
        dogs.add(Dog(i++, "https://images.dog.ceo/breeds/dingo/n02115641_1380.jpg"))
        dogs.add(Dog(i++, "https://images.dog.ceo/breeds/malamute/n02110063_2481.jpg"))
        dogs.add(Dog(i++, "https://images.dog.ceo/breeds/labrador/n02099712_7133.jpg"))
        dogs.add(Dog(i++, "https://images.dog.ceo/breeds/appenzeller/n02107908_3991.jpg"))
        dogs.add(Dog(i++, "https://images.dog.ceo/breeds/akita/512px-Akita_inu.jpeg"))
        dogs.add(Dog(i++, "https://images.dog.ceo/breeds/african/n02116738_4367.jpg"))
        nombre = i-1;
    }
    fun add(dog: Dog){
        dogs.add(dog)
        nombre++
    }
    fun getByIndex(index : Int) : Dog{
        return dogs.get(index)
    }
    fun getById(id : Int) : Dog{
        for (i in 0 until nombre) {
            if(id == dogs.get(i).id){
                return dogs.get(id)
            }
        }
        return Dog(-1, "")
    }
}