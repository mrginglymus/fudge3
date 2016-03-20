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
        DateThingGroupSetKey key = getKey(datedThing);
        m.putIfAbsent(key, new DateThingGroup(datedThing.getDate(), datedThing.getMyEnumSet()));
        m.get(key).add(datedThing);
    }

    static private DateThingGroupSetKey getKey(DatedThing datedThing) {
        return new DateThingGroupSetKey(datedThing.getDate(), datedThing.getMyEnumSet());
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

    public DateThingGroupSetKey(LocalDate date, EnumSet<MyEnum> myEnumSet) {
        this.date = date;
        this.myEnumSet = myEnumSet;
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
