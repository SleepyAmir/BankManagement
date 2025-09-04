package com.sleepy.bankmanagement.Controller;

import com.sleepy.bankmanagement.entity.Card;
import com.sleepy.bankmanagement.entity.CardType;
import com.sleepy.bankmanagement.service.CardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;

@WebServlet(name = "cards", urlPatterns = "/cards")
public class CardServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            CardService cardService = new CardService();
            req.getSession().setAttribute("cardList", cardService.findAll());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error Find: " + e.getMessage());
        }

        req.getRequestDispatcher("/card.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            CardService cardService = new CardService();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Card card = Card
                    .builder()
                    .cardNumber(req.getParameter("cardNumber"))
                    .cardType(CardType.valueOf(req.getParameter("cardType")))
                    .linkedAccountNumber(req.getParameter("linkedAccountNumber"))
                    .expiryDate(dateFormat.parse(req.getParameter("expirationDate")))
                    .cvv(req.getParameter("cvv"))
                    .isActive(Boolean.parseBoolean(req.getParameter("isActive")))
                    .build();

            cardService.save(card);
            System.out.println("Card Saved Successfully");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error Save: " + e.getMessage());
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}