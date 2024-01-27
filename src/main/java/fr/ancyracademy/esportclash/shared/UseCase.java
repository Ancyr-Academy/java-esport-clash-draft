package fr.ancyracademy.esportclash.shared;

public interface UseCase<TRequest, TResponse> {
  TResponse execute(TRequest request) throws RuntimeException;
}
