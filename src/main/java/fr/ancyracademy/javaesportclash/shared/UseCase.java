package fr.ancyracademy.javaesportclash.shared;

public interface UseCase<TRequest, TResponse> {
  TResponse execute(TRequest request);
}
