package org.exam.java.spring.asset_manager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "The name must not be null, nor empty or blank")
    @Column(nullable = false, unique = true)
    private String name;

    @NotBlank(message = "The description must not be null, nor empty or blank")
    @Column(length = 1000)
    private String description;

    @NotNull(message = "Risk level is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "risk_level", nullable = false)
    private Category.RiskLevel riskLevel;

    // ***
    public enum RiskLevel {

        LOW("Low risk", "success", 1),
        MEDIUM("Medium risk", "warning", 2),
        HIGH("High risk", "danger", 3);

        private final String label;
        private final String badgeClass;
        private final int order;

        RiskLevel(String label, String badgeClass, int order) {
            this.label = label;
            this.badgeClass = badgeClass;
            this.order = order;
        }

        public String getLabel() {
            return label;
        }

        public String getBadgeClass() {
            return badgeClass;
        }

        public int getOrder() {
            return order;
        }
    }
    // ***

    // @OneToMany(mappedBy = "pizza", cascade = { CascadeType.REMOVE })
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    @JsonManagedReference
    @JsonIgnore
    private List<Asset> assets;

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public Category.RiskLevel getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(Category.RiskLevel riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
