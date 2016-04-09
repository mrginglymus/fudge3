package works.bill.service;

import org.apache.commons.collections.SetUtils;
import works.bill.entities.DatedThing;
import works.bill.entities.MyEnum;

import java.time.LocalDate;
import java.util.*;

/**
 * Created by Bill on 20/03/2016.
 */
public class DateThingGroupSet extends AbstractSet<DateThingGroup> {

    private Map<DateThingGroupSetKey, DateThingGroup> m = new TreeMap<>();

    public void add(DatedThing datedThing) {
        DateThingGroup grp = getOrCreate(getKey(datedThing));
        grp.add(datedThing);
    }

    static private DateThingGroupSetKey getKey(DatedThing datedThing) {
        return new DateThingGroupSetKey(datedThing.getDate(), datedThing.getMyEnumSet());
    }

    private DateThingGroup getOrCreate(DateThingGroupSetKey key) {
        return m.computeIfAbsent(key, DateThingGroup::new);
    }

    @Override
    public Iterator<DateThingGroup> iterator() {
        return m.values().iterator();
    }

    @Override
    public int size() {
        return m.size();
    }
}

class DateThingGroupSetKey implements Comparable<DateThingGroupSetKey> {

    private LocalDate date;

    private EnumSet<MyEnum> myEnumSet;

    DateThingGroupSetKey(LocalDate date, EnumSet<MyEnum> myEnumSet) {
        this.date = date;
        this.myEnumSet = myEnumSet;
    }

    LocalDate getDate() {
        return date;
    }

    EnumSet<MyEnum> getMyEnumSet() {
        return myEnumSet;
    }

    @Override
    public int compareTo(DateThingGroupSetKey o) {
        if (this.date.isEqual(o.date)) {
            if (SetUtils.isEqualSet(this.myEnumSet, o.myEnumSet)) {
                return 0;
            }
            return this.myEnumSet.hashCode() < o.myEnumSet.hashCode() ? -1 : 1;
        }
        return this.date.isBefore(o.date) ? -1 : 1;
    }
}
