package works.bill.service;

import works.bill.entities.DatedThing;
import works.bill.entities.MyEnum;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.HashSet;

/**
 * Created by Bill on 20/03/2016.
 */
public class DateThingGroup extends HashSet<DatedThing> implements Comparable<DateThingGroup> {

    private LocalDate date;

    private EnumSet<MyEnum> myEnumSet;

    public DateThingGroup(LocalDate date, EnumSet<MyEnum> myEnumSet) {
        this.date = date;
        this.myEnumSet = myEnumSet;
    }

    public LocalDate getDate() {
        return date;
    }

    public EnumSet<MyEnum> getMyEnumSet() {
        return myEnumSet;
    }

    @Override
    public int compareTo(DateThingGroup o) {
        if (this.date.isEqual(o.date)) {
            return 0;
        }
        return this.date.isBefore(o.date) ? -1 : 1;
    }
}
