package source.interfaces;

import source.classes.Admin;
import source.enums.ApplyStatus;

/*
 * An class that implements IStatus should contain
 * the enum value `ApplyStatus` and allow it to be set
 * and retrieved.
 * 
 * Also, the status can contain its description, and the
 * responsible admin that set the status
*/
public interface IStatus {
    public ApplyStatus getStatus();
    public String getStatusDescription();
    public Admin getStatusAdmin();
    public void setStatus(ApplyStatus status, String desc, Admin admin);
}
