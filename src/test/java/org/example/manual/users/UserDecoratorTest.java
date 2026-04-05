package org.example.manual.users;

import org.example.users.Student;
import org.example.users.User;
import org.example.users.UserDecorator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserDecoratorTest {

    @Test
    public void testDecoratorWrapsUserFields() {
        User base = new Student("STU-001", "Alice", "alice@york.ca", "Pass123!", "CS", "ID100");
        UserDecorator decorated = new UserDecorator(base, "APPROVAL");

        assertEquals("STU-001", decorated.getUserId());
        assertEquals("Alice", decorated.getName());
        assertEquals("alice@york.ca", decorated.getEmail());
        assertEquals("Pass123!", decorated.getPassword());
        assertEquals("CS", decorated.getDepartmentId());
        assertEquals("ID100", decorated.getIdNumber());
    }

    @Test
    public void testGetUserTypeDelegatesToWrappedUser() {
        User base = new Student("STU-002", "Bob", "bob@york.ca", "Pass123!", "EE", "ID101");
        UserDecorator decorated = new UserDecorator(base, "APPROVAL");
        assertEquals("STUDENT", decorated.getUserType());
    }

    @Test
    public void testDefaultApprovalStatusIsNotApproved() {
        User base = new Student("STU-003", "Carol", "carol@york.ca", "Pass123!", "CS", "ID102");
        UserDecorator decorated = new UserDecorator(base, "APPROVAL");
        assertEquals("NOT_APPROVED", decorated.getApprovalStatus());
        assertFalse(decorated.isApproved());
    }

    @Test
    public void testSetApprovalStatusToApproved() {
        User base = new Student("STU-004", "Dan", "dan@york.ca", "Pass123!", "CS", "ID103");
        UserDecorator decorated = new UserDecorator(base, "APPROVAL");
        decorated.setApprovalStatus("APPROVED");

        assertEquals("APPROVED", decorated.getApprovalStatus());
        assertTrue(decorated.isApproved());
    }

    @Test
    public void testIsApprovedReturnsFalseForArbitraryStatus() {
        User base = new Student("STU-005", "Eve", "eve@york.ca", "Pass123!", "CS", "ID104");
        UserDecorator decorated = new UserDecorator(base, "APPROVAL");
        decorated.setApprovalStatus("PENDING");

        assertFalse(decorated.isApproved());
    }

    @Test
    public void testGetDecorationTypeReturnsApproval() {
        User base = new Student("STU-006", "Frank", "frank@york.ca", "Pass123!", "CS", "ID105");
        UserDecorator decorated = new UserDecorator(base, "APPROVAL");
        assertEquals("APPROVAL", decorated.getDecorationType());
    }

    @Test
    public void testGetDecorationTypeReturnsCertification() {
        User base = new Student("STU-007", "Grace", "grace@york.ca", "Pass123!", "CS", "ID106");
        UserDecorator decorated = new UserDecorator(base, "CERTIFICATION");
        assertEquals("CERTIFICATION", decorated.getDecorationType());
    }

    @Test
    public void testGetCertificationNumberDefaultsToNull() {
        User base = new Student("STU-008", "Henry", "henry@york.ca", "Pass123!", "CS", "ID107");
        UserDecorator decorated = new UserDecorator(base, "CERTIFICATION");
        assertNull(decorated.getCertificationNumber());
    }

    @Test
    public void testDecoratorIsInstanceOfUser() {
        User base = new Student("STU-009", "Irene", "irene@york.ca", "Pass123!", "CS", "ID108");
        UserDecorator decorated = new UserDecorator(base, "APPROVAL");
        assertTrue(decorated instanceof User);
        assertTrue(decorated instanceof UserDecorator);
    }

    @Test
    public void testDecoratorSettersUpdateDecoratedFields() {
        User base = new Student("STU-010", "Jack", "jack@york.ca", "Pass123!", "CS", "ID109");
        UserDecorator decorated = new UserDecorator(base, "APPROVAL");
        decorated.setName("Jackson");
        decorated.setEmail("jackson@york.ca");

        assertEquals("Jackson", decorated.getName());
        assertEquals("jackson@york.ca", decorated.getEmail());
    }
}

