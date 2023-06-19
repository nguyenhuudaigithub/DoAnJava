package com.JS.DA.Cart.Repository;

import com.JS.DA.Cart.Model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInvoiceRepository extends JpaRepository<Invoice, Long> {
}