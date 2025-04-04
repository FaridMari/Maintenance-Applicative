package MyCalendar.calendar.domain;

import MyCalendar.calendar.domain.valueObject.EventId;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Calendrier {
    private final List<Evenement> evenements = new ArrayList<>();

    public void ajouter(Evenement evenement) {
        evenements.add(evenement);
    }

    public List<Evenement> evenementsDansPeriode(LocalDateTime debut, LocalDateTime fin) {
        return evenements.stream()
                .filter(e -> !e.dateDebut.isBefore(debut) && !e.dateDebut.isAfter(fin))
                .toList();
    }

    public List<Evenement> tous() {
        return List.copyOf(evenements);
    }

    public void supprimerParId(EventId id) {
        evenements.removeIf(e -> e.id().equals(id));
    }

    public boolean estEnConflitAvec(Evenement nouveau) {
        return evenements.stream()
                .anyMatch(e -> e.chevauche(nouveau));
    }

    public String decrirePeriode(LocalDateTime debut, LocalDateTime fin) {
        return evenementsDansPeriode(debut, fin).stream()
                .map(Evenement::description)
                .collect(Collectors.joining("\n"));
    }


}
