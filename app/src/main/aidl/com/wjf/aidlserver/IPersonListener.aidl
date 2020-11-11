// IPersonListener.aidl
package com.wjf.aidlserver;

// Declare any non-default types here with import statements
import com.wjf.aidlserver.entity.MyPerson;

interface IPersonListener {
    List<MyPerson> getPersons();
    boolean addPerson(inout MyPerson myperson);
    int personSize();
}
