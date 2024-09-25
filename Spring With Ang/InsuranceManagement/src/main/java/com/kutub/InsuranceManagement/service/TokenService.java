package com.kutub.InsuranceManagement.service;

import com.kutub.InsuranceManagement.entity.Token;
import com.kutub.InsuranceManagement.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TokenService {

    private final TokenRepository tokenRepository;

    @Autowired
    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    // Get all active tokens by user ID
    public List<Token> getActiveTokensByUser(Long userId) {
        return tokenRepository.findAllTokensByUser(userId);
    }

    // Find a token by its token string value
    public Optional<Token> findToken(String token) {
        return tokenRepository.findByToken(token);
    }
}
