package org.example.repositories;

import org.example.Models.Address;

import java.sql.SQLException;

public interface AddressRepository extends CrudRepository<Address> {
    Long findIdByAddress(Address address) throws SQLException;


}
