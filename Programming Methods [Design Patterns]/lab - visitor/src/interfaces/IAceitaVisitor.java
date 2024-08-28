package interfaces;

public interface IAceitaVisitor {
      <T> T aceitarVisitor(IVisitor<T> visitor);
}
