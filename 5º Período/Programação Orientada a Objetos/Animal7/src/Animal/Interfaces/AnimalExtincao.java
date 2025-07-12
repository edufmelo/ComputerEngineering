package Animal.Interfaces;

// é um subinterface, quem implementar AnimalExtincao precisa implementar seProteger
// e também o pedido da superinterface, AnimalSelvagem, que é o método cacar
public interface AnimalExtincao extends AnimalSelvagem {

    public String seProteger();
}
