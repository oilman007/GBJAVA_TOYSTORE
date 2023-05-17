package raffle;

import java.util.Objects;

public class Toy implements Comparable<Toy> {

        private int toyId;
        private String toyName;
        private int frequencyFallingToy;

        public Toy(int toyId, String toyName, int frequencyFallingToy) {
            this.toyId = toyId;
            this.toyName = toyName;
            this.frequencyFallingToy = frequencyFallingToy;
        }

        public int getToyId() {
            return toyId;
        }

        public String getToyName() {
            return toyName;
        }

        public int getFrequencyFallingToy() {
            return frequencyFallingToy;
        }

        public void setFrequencyFallingToy(int frequencyFallingToy) {
            this.frequencyFallingToy = frequencyFallingToy;
        }

        public String getInfo() {
            return String.format("ID: %d, название игрушки: %s", toyId, toyName);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Toy toy = (Toy) o;
            return toyName.equals(toy.toyName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(toyName);
        }

        @Override
        public int compareTo(Toy o) {
            return Integer.compare(this.frequencyFallingToy, o.frequencyFallingToy);
        }
}


